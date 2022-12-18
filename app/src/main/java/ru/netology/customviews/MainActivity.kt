package ru.netology.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.customviews.ui.CustomViews

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<CustomViews>(R.id.customView).data = listOf(
            0.25F,
            0.25F,
            0.25F,
            0.25F,
        )
    }
}