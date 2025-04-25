package com.example.budgettracker.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgettracker.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?
}