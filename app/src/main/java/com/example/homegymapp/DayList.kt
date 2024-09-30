package com.example.homegymapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homegymapp.databinding.ActivityDayListBinding
import kotlinx.coroutines.launch

class DayList : AppCompatActivity() {
    private lateinit var binding: ActivityDayListBinding
    private lateinit var adapter: DayListAdapter
    private lateinit var list: ArrayList<DayListModel>
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = UserDatabase.getDatabase(this)
        val muscleGroup = intent.getStringExtra("muscle_group")

        list = ArrayList()
        adapter = DayListAdapter(list) // Set up adapter before fetching data
        binding.daylistrecycle.layoutManager = LinearLayoutManager(this@DayList)
        binding.daylistrecycle.adapter = adapter

        lifecycleScope.launch {
            if (muscleGroup != null) {
                try {
                    val days = database.daydataDao().getDaysByMuscleGroup(muscleGroup)
                    if (days.isNotEmpty()) {
                        for (dayEntity in days) {
                            list.add(DayListModel(dayEntity.dayNumber.toString(), dayEntity.muscleGroup))
                        }
                        adapter.notifyDataSetChanged() // Notify adapter after data has been fetched
                    } else {
                        Toast.makeText(this@DayList, "No data available for $muscleGroup", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@DayList, "Error fetching data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.backButton.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}