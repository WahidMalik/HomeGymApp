package com.example.homegymapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homegymapp.databinding.ActivityDayListBinding

class DayList : AppCompatActivity() {
    lateinit var binding: ActivityDayListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}