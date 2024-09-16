package com.example.homegymapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homegymapp.databinding.ActivityDayListBinding

class DayList : AppCompatActivity() {
    lateinit var binding: ActivityDayListBinding
    lateinit var adapter: DayListAdapter
    lateinit var list: ArrayList<DayListModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDayListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        list.add(DayListModel("Day 1", "Arm Exercises"))
        list.add(DayListModel("Day 2", "Leg Exercises"))
        list.add(DayListModel("Day 3", "Back Exercises"))
        list.add(DayListModel("Day 4", "Chest Exercises"))
        list.add(DayListModel("Day 5", "Shoulder Exercises"))
        list.add(DayListModel("Day 6", "Abs Exercises"))
        list.add(DayListModel("Day 7", "Cardio Exercises"))

        adapter = DayListAdapter(list)
        binding.daylistrecycle.layoutManager = LinearLayoutManager(this)
        binding.daylistrecycle.adapter = adapter




    }
}