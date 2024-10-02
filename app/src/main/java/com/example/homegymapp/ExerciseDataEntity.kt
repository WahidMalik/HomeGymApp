package com.example.homegymapp

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise_table",
    foreignKeys = [
        ForeignKey(entity = DaydataEntity::class, parentColumns = ["id"], childColumns = ["dayId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class ExerciseDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var image: Int,
    var videoUrl: String,
    var dayId: Int,
    var muscleGroup: String,
    var time: String
)
