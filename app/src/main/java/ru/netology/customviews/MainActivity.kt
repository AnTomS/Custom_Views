package ru.netology.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import ru.netology.customviews.ui.CustomViews

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customView = findViewById<CustomViews>(R.id.customView)
        val data = listOf(
            500F,
            500F,
            500F,
            500F,
        )
        customView.data = data.map {
            it / data.sum()
        }
    }

//
//        val textViews = findViewById<TextView>(R.id.label)
//
//        customView.startAnimation(
//            AnimationUtils.loadAnimation(this, R.anim.animation).apply {
//                setAnimationListener(object : Animation.AnimationListener {
//                    override fun onAnimationStart(p0: Animation?) {
//                        textViews.text = "onAnimationStart"
//                    }
//
//                    override fun onAnimationEnd(p0: Animation?) {
//                        textViews.text = "onAnimationEnd"
//                    }
//
//                    override fun onAnimationRepeat(p0: Animation?) {
//                        textViews.text = "onAnimationRepeat"
//                    }
//
//                })
//            }
//        )
//    }
}
