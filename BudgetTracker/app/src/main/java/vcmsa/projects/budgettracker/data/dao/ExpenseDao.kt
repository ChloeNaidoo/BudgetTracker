package com.example.budgettracker.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgettracker.data.Expense
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense: Expense)

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getExpensesInDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<Expense>>

    @Query(
        """
        SELECT c.name, SUM(e.expenseId) AS totalSpent 
        FROM expenses e 
        INNER JOIN categories c ON e.categoryId = c.categoryId 
        WHERE e.date BETWEEN :startDate AND :endDate 
        GROUP BY c.categoryId
        """
    )
    fun getTotalSpentByCategoryInDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<CategoryTotal>>
}

data class CategoryTotal(
    val name: String,
    val totalSpent: Double // Assuming expenseId represents the amount for simplicity
    // In a real app, you'd have an 'amount' field in Expense
)