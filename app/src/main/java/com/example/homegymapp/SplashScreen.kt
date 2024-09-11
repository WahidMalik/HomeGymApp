package com.example.homegymapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.homegymapp.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slideUp: Animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_left)
        binding.icon.startAnimation(slideUp)
        binding.title.startAnimation(slideUp)
        binding.description.startAnimation(slideUp)

        android.os.Handler().postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
        }
    }
