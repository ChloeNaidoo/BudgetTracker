package com.example.budgettracker.userint.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgettracker.data.User
import com.example.budgettracker.data.daos.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class RegistrationViewModel(private val userDao: UserDao) : ViewModel() {
    private val _registrationResult = MutableStateFlow<RegistrationResult>(RegistrationResult.Idle)
    val registrationResult: StateFlow<RegistrationResult> = _registrationResult

    fun register(username: String, password: String) {
        _registrationResult.value = RegistrationResult.Loading
        viewModelScope.launch {
            if (userDao.getUserByUsername(username) == null) {
                val passwordHash = hashString(password)
                userDao.insert(User(username = username, passwordHash = passwordHash.toString(Charsets.UTF_8)))
                _registrationResult.value = RegistrationResult.Success
            } else {
                _registrationResult.value = RegistrationResult.Error("Username already exists")
            }
        }
    }

    // Simple SHA-256 hashing (for demonstration purposes - consider more robust solutions)
    private fun hashString(input: String): ByteArray {
        return MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
    }
}

sealed class RegistrationResult {
    object Idle : RegistrationResult()
    object Loading : RegistrationResult()
    object Success : RegistrationResult()
    data class Error(val message: String) : RegistrationResult()
}