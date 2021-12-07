package com.dailystore.viewmodels.auth

import androidx.lifecycle.ViewModel
import com.dailystore.repositories.AuthRepository
import com.dailystore.views.auth.AuthListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import androidx.databinding.ObservableField
import com.dailystore.utils.isEmailValid
import com.dailystore.utils.isPasswordValid
import com.google.firebase.auth.AuthCredential


class SignInViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var authListener: AuthListener? = null

    private val disposables = CompositeDisposable()

    val user by lazy {
        authRepository.currentUser()
    }

    fun signInEmail() {
        if (email.get().isNullOrEmpty() or password.get().isNullOrEmpty()) {
            authListener?.onFailure("Please input all fields")
            return
        }

        if (!(isEmailValid(email.get()!!) and isPasswordValid(password.get()!!))) {
            authListener?.onFailure("Not all fields are valid")
            return
        }

        authListener?.onStarted()

        val disposable = authRepository.signIn(email.get()!!, password.get()!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun resetPassword() {
        if (email.get().isNullOrEmpty()) {
            authListener?.onFailure("Please input your email")
            return
        }

        if (!(isEmailValid(email.get()!!))) {
            authListener?.onFailure("Please input valid email")
            return
        }

        authListener?.onStarted()

        val disposable = authRepository.resetPassword(email.get()!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    fun signInWithExternalAuthProvider(authCredential: AuthCredential){
        authListener?.onStarted()

        val disposable = authRepository.signInWithExternalAuthProvider(authCredential)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}