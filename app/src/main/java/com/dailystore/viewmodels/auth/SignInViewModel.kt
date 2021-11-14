package com.dailystore.viewmodels.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.dailystore.repositories.AuthRepository
import com.dailystore.views.auth.AuthListener
import com.dailystore.views.auth.SignUpActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import android.app.Activity
import com.dailystore.R


class SignInViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        authRepository.currentUser()
    }

    fun signInEmail() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        authListener?.onStarted()

        val disposable = authRepository.signIn(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun signInFacebook() {
        // TODO: 11/14/2021 Facebook sign in
    }

    fun signInGoogle() {
        // TODO: 11/14/2021 Google sign in
    }

    fun restorePassword() {
        // TODO: 11/14/2021 Password restore
    }

    fun goToSignIn(view: View) {
        val activity = view.context as Activity
        activity.startActivity(Intent(view.context, SignUpActivity::class.java))
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
        activity.finish()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}