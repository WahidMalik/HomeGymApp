package com.example.homegymapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.homegymapp.databinding.ActivityHomePageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = UserDatabase.getDatabase(this)

        binding.arm.setOnClickListener { navigateToDayList("Arm") }
        binding.abs.setOnClickListener { navigateToDayList("Abs") }
        binding.chest.setOnClickListener { navigateToDayList("Chest") }
        binding.leg.setOnClickListener { navigateToDayList("Leg") }
        binding.shoulders.setOnClickListener { navigateToDayList("Shoulders") }
        binding.back.setOnClickListener { navigateToDayList("Back") }

        checkAndInsertDays()
    }

    private fun navigateToDayList(muscleGroup: String) {
        val intent = Intent(this, DayList::class.java)
        intent.putExtra("muscle_group", muscleGroup)
        startActivity(intent)
    }

    private fun checkAndInsertDays() {
        val sharedPreferences = getSharedPreferences("com.example.homegymapp", Context.MODE_PRIVATE)
        val daysInserted = sharedPreferences.getBoolean("days_inserted", false)

        if (!daysInserted) {
            insertDays()
            // Mark days as inserted
            sharedPreferences.edit().putBoolean("days_inserted", true).apply()
        }
    }

    private fun insertDays() {
        val muscles = listOf("Arm", "Chest", "Shoulders", "Leg", "Back", "Abs")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                for (muscle in muscles) {
                    for (i in 1..30) {
                        val day = DaydataEntity(id = 0, dayNumber = i, muscleGroup = muscle)
                        database.daydataDao().insertDays(day)
                    }
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@HomePage, "Days inserted successfully", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@HomePage, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
