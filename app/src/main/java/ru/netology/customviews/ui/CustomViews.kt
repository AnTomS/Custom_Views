package ru.netology.customviews.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import ru.netology.customviews.R
import ru.netology.customviews.utils.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class CustomViews @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,

    ) : View(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes,
) {

    private var textSize = AndroidUtils.dp(context, 20).toFloat()
    private var lineWidth = AndroidUtils.dp(context, 5)
    private var colors = emptyList<Int>()
    private var progress = 0F

    init {
        context.withStyledAttributes(attributeSet, R.styleable.CustomViews) {
            textSize = getDimension(R.styleable.CustomViews_textSize, textSize)
            lineWidth = getDimension(R.styleable.CustomViews_lineWidth, lineWidth.toFloat()).toInt()

            colors = listOf(
                getColor(R.styleable.CustomViews_color1, generateRandomColor()),
                getColor(R.styleable.CustomViews_color2, generateRandomColor()),
                getColor(R.styleable.CustomViews_color3, generateRandomColor()),
                getColor(R.styleable.CustomViews_color4, generateRandomColor()),
            )
        }
    }


    var data: List<Float> = emptyList()
        set(value) {
            field = value
            invalidate()
        }
    private var radius = 0F
    private var center = PointF()
    private var oval = RectF()

    private val paint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        strokeWidth = lineWidth.toFloat()
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        textSize = this@CustomViews.textSize
        strokeWidth = lineWidth.toFloat()
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2F - lineWidth
        center = PointF(w / 2F, h / 2F)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius,
        )

    }

    override fun onDraw(canvas: Canvas) {
        if (data.isEmpty()) {
            return
        }
        // определяем переменную стартовый угол поворота,
        // он 90 градусов чтобы начинать отрисовку сверху
        var startAngle = -90F



        data.forEachIndexed { index, datum ->
            //расчитаем угол поворотам каждого элемента.
            // умножим данные на 360 чтобы получить угол
            val angle = (datum / data.sum())*360F
            paint.color = colors.getOrElse(index) { generateRandomColor() }
            canvas.drawArc(oval, startAngle, angle, false, paint)
            startAngle += angle
        }
        paint.color = colors[0]
        canvas.drawArc(oval, startAngle, 8F , false, paint)

        canvas.drawText(
            "%.2f%%".format(data.sum() * 100),
            center.x,
            center.y + textPaint.textSize / 4,
            textPaint
        )

    }

    private fun generateRandomColor() = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
}