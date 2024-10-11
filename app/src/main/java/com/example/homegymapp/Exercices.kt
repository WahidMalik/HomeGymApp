package com.example.homegymapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homegymapp.databinding.ActivityExercicesBinding
import kotlinx.coroutines.launch

class Exercices : AppCompatActivity() {

    private lateinit var binding: ActivityExercicesBinding
    private lateinit var adapter: ExerciseAdapter
    private lateinit var list: ArrayList<ExerciseData>
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercicesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = UserDatabase.getDatabase(this)
        val muscleGroup = intent.getStringExtra("muscle_group1")
        val dayNumber = intent.getStringExtra("day_number")?.toIntOrNull()

        binding.dayNumber.text ="Day "+ dayNumber.toString() + " Exercises"
        binding.muscleName.text = muscleGroup + " Exercises"

        list = ArrayList()
        adapter = ExerciseAdapter(list)
        binding.exercisesrecycle.layoutManager = LinearLayoutManager(this@Exercices)
        binding.exercisesrecycle.adapter = adapter

        Toast.makeText(this@Exercices, "Day Number: $dayNumber", Toast.LENGTH_SHORT).show()
        Toast.makeText(this@Exercices, "Muscle Group: $muscleGroup", Toast.LENGTH_SHORT).show()

        lifecycleScope.launch {
            if (muscleGroup != null && dayNumber != null) {
                try {
                    val exercises = database.exerciseDao().getExercisesByDayIdAndMuscleGroup(dayNumber, muscleGroup)
                    if (exercises.isNotEmpty()) {
                        for (exercise in exercises) {
                            list.add(
                                ExerciseData(
                                    exercise.name,
                                    exercise.image.toString(), // Assuming image is a resource ID, convert to string if needed
                                    exercise.videoUrl,
                                    exercise.muscleGroup,
                                    exercise.time
                                )
                            )
                        }
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@Exercices, "No data available for $muscleGroup", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@Exercices, "Error fetching data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@Exercices, "Invalid day number or muscle group", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
