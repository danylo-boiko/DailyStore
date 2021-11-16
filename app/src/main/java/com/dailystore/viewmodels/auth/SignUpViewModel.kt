package com.dailystore.viewmodels.auth

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dailystore.R
import com.dailystore.repositories.AuthRepository
import com.dailystore.utils.isEmailValid
import com.dailystore.utils.isPasswordValid
import com.dailystore.utils.isUsernameValid
import com.dailystore.views.auth.AuthListener
import com.dailystore.views.auth.SignInActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var username = ObservableField<String>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var confirmPassword = ObservableField<String>()
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        authRepository.currentUser()
    }

    fun signUpEmail() {
        if (username.get().isNullOrEmpty() or email.get().isNullOrEmpty() or
            password.get().isNullOrEmpty() or confirmPassword.get().isNullOrEmpty()) {
            authListener?.onFailure("Please input all fields")
            return
        }

        if (!(isUsernameValid(username.get()!!) and isEmailValid(email.get()!!) and
            isPasswordValid(password.get()!!) and (password.get()!! == confirmPassword.get()!!))) {
            authListener?.onFailure("Not all fields are valid")
            return
        }

        authListener?.onStarted()

        val disposable = authRepository.signUp(username.get()!!, email.get()!!, password.get()!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun signUpFacebook() {
        authListener?.onFailure("Todo sign up facebook")
        // TODO: 11/14/2021 Facebook sign up
    }

    fun signUpGoogle() {
        authListener?.onFailure("Todo sign up google")
        // TODO: 11/14/2021 Google sign up
    }

    fun goToSignIn(view: View) {
        val activity = view.context as Activity
        activity.startActivity(Intent(view.context, SignInActivity::class.java))
        activity.overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)
        activity.finish()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}