package com.example.homegymapp

data class ExerciseData(
    var name: String,
    var image: String,
    val videoUrl: String,
    val muscleGroup: String,
    val time: String
)
