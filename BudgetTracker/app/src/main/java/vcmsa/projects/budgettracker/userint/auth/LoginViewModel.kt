package com.example.budgettracker.userint.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgettracker.data.User
import com.example.budgettracker.data.daos.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class LoginViewModel(private val userDao: UserDao) : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Idle)
    val loginResult: StateFlow<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        _loginResult.value = LoginResult.Loading
        viewModelScope.launch {
            val user = userDao.getUserByUsername(username)
            if (user != null && checkPassword(password, user.passwordHash)) {
                _loginResult.value = LoginResult.Success
            } else {
                _loginResult.value = LoginResult.Error("Invalid username or password")
            }
        }
    }

    private fun checkPassword(password: String, hash: String): Boolean {
        val passwordHash = hashString(password)
        return passwordHash.contentEquals(hash.toByteArray())
    }

    // Simple SHA-256 hashing (for demonstration purposes - consider more robust solutions)
    private fun hashString(input: String): ByteArray {
        return MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
    }
}

sealed class LoginResult {
    object Idle : LoginResult()
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}