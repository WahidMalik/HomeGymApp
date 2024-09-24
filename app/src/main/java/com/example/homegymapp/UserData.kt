package com.example.homegymapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var userName: String,
    var password: String
)
