package com.example.homegymapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homegymapp.databinding.ActivityExercicesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Exercices : AppCompatActivity() {

    private lateinit var binding: ActivityExercicesBinding
    private lateinit var database: UserDatabase
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var adapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = UserDatabase.getDatabase(this)
        exerciseDao = database.exerciseDao()

        val dayId = intent.getIntExtra("dayId", -1)

        if (dayId != -1) {
            loadExercises(dayId)
        } else {
            Toast.makeText(this, "Invalid day ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadExercises(dayId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val exercises = exerciseDao.getExercisesByDayId(dayId) // Modify this line as per your DAO method
            withContext(Dispatchers.Main) {
                if (exercises.isNotEmpty()) {
                    adapter = ExerciseAdapter(exercises)
                    binding.exercisesrecycle.adapter = adapter
                } else {
                    Toast.makeText(this@Exercices, "No exercises found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
