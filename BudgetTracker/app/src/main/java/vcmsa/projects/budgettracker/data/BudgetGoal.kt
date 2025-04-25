package com.example.budgettracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_goals")
data class BudgetGoal(
    @PrimaryKey(autoGenerate = true) val goalId: Long = 0,
    val minimumMonthlyGoal: Double? = null,
    val maximumMonthlyGoal: Double? = null
)