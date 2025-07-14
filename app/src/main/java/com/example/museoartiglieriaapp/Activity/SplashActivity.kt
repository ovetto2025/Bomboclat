package com.example.museoartiglieriaapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.museoartiglieriaapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.splashLogo)
        logo.scaleX = 3.5f
        logo.scaleY = 3.5f
        logo.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(900)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                logo.postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 1000)
            }
            .start()
    }
} 