package com.dailystore.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dailystore.repositories.AuthRepository

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(authRepository) as T
        }else if (modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(authRepository) as T
        }else{
            throw IllegalArgumentException("Auth viewmodel not found")
        }
    }
}