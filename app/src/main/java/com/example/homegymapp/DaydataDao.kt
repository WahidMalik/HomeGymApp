package com.example.homegymapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaydataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDays(days: DaydataEntity)

    @Query("SELECT * FROM daydataentity WHERE muscleGroup = :muscleGroup")
    suspend fun getDaysByMuscleGroup(muscleGroup: String): List<DaydataEntity>
}
