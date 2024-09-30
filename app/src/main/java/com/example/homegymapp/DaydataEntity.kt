package com.example.homegymapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daydataentity")
data class DaydataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dayNumber: Int,
    val muscleGroup: String
)
