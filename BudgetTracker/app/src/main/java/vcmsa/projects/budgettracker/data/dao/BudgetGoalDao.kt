package com.example.budgettracker.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.budgettracker.data.BudgetGoal
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetGoalDao {
    @Insert
    suspend fun insert(budgetGoal: BudgetGoal)

    @Query("SELECT * FROM budget_goals LIMIT 1")
    fun getBudgetGoal(): Flow<BudgetGoal?>

    @Update
    suspend fun update(budgetGoal: BudgetGoal)
}