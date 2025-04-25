package com.example.budgettracker.userint.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgettracker.data.Category
import com.example.budgettracker.data.daos.CategoryDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryDao: CategoryDao) : ViewModel() {
    val categories = categoryDao.getAllCategories()
    private val _addCategoryResult = MutableStateFlow<AddCategoryResult>(AddCategoryResult.Idle)
    val addCategoryResult: StateFlow<AddCategoryResult> = _addCategoryResult

    fun addCategory(name: String) {
        if (name.isNotBlank()) {
            viewModelScope.launch {
                categoryDao.insert(Category(name = name))
                _addCategoryResult.value = AddCategoryResult.Success
            }
        } else {
            _addCategoryResult.value = AddCategoryResult.Error("Category name cannot be empty")
        }
    }

    fun resetAddCategoryResult() {
        _addCategoryResult.value = AddCategoryResult.Idle
    }
}

sealed class AddCategoryResult {
    object Idle : AddCategoryResult()
    object Success : AddCategoryResult()
    data class Error(val message: String) : AddCategoryResult()
}