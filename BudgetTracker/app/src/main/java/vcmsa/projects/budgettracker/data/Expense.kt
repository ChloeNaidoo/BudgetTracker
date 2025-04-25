package com.example.budgettracker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true) val expenseId: Long = 0,
    val date: LocalDateTime,
    val startTime: String,
    val endTime: String,
    val description: String,
    val categoryId: Long,
    val photoPath: String? = null // Optional photo path
)