package ru.netology.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}
