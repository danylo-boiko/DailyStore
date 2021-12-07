package com.dailystore.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dailystore.repositories.AuthRepository


@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory (private val authRepository: AuthRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(authRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Auth view model not found")
            }
        }
    }
}