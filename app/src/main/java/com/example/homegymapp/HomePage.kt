package com.example.homegymapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.homegymapp.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arm.setOnClickListener {
            val intent = Intent(this@HomePage, DayList::class.java)
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            val intent = Intent(this@HomePage, Exercices::class.java)
            startActivity(intent)
        }
        binding.backButton.setOnClickListener {
            onBackPressed()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}