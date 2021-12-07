package com.dailystore.viewmodels.home

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.dailystore.repositories.AuthRepository
import com.dailystore.views.auth.SignInActivity

class HomeViewModel (private val authRepository: AuthRepository) : ViewModel() {
    fun logout(view: View){
        authRepository.signOut()

        val activity = view.context as Activity
        activity.startActivity(Intent(view.context, SignInActivity::class.java))
        activity.finish()
    }
}