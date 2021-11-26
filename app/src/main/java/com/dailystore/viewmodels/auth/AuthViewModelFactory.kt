package com.dailystore.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dailystore.repositories.AuthRepository
import com.dailystore.repositories.UsersRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val authRepository: AuthRepository, private val usersRepository: UsersRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(authRepository, usersRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Auth view model not found")
            }
        }
    }
}