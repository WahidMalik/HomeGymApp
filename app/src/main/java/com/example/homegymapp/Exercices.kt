package com.example.homegymapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homegymapp.databinding.ActivityExercicesBinding

class Exercices : AppCompatActivity() {
    lateinit var binding: ActivityExercicesBinding
    lateinit var adapter: ExerciseAdapter
    lateinit var list: ArrayList<ExerciseData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExercicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        list = ArrayList()
        list.add(ExerciseData(R.drawable.shoulders, "Shoulders"))
        list.add(ExerciseData(R.drawable.back, "Back"))
        list.add(ExerciseData(R.drawable.abs, "Abs"))

        adapter = ExerciseAdapter(list)
        binding.exercisesrecycle.layoutManager = LinearLayoutManager(this)
        binding.exercisesrecycle.adapter = adapter



    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}