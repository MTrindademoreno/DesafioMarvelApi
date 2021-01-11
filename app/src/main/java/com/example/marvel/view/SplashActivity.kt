package com.example.marvel.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvel.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        Timer("SplashScreen", false).schedule(2000) {
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            finish()
        }
    }
}