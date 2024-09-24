package com.example.homegymapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserData)


    @Query("SELECT * FROM user_table WHERE userName = :username AND password = :password LIMIT 1")
    suspend fun getUser(username: String, password: String): UserData?

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUser(): LiveData<List<UserData>>

}