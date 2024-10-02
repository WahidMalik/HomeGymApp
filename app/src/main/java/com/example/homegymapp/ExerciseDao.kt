package com.example.homegymapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(exercise: ExerciseDataEntity)

    @Query("SELECT * FROM exercise_table WHERE dayId = :dayId")
    suspend fun getExercisesByDayId(dayId: Int): List<ExerciseDataEntity>
}
