package com.dailystore.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dailystore.repositories.AuthRepository

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInViewModel (authRepository) as T
    }
}