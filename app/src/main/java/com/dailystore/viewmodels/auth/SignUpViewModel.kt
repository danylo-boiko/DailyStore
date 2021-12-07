package com.dailystore.viewmodels.auth

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dailystore.models.User
import com.dailystore.repositories.AuthRepository
import com.dailystore.repositories.UsersRepository
import com.dailystore.utils.isEmailValid
import com.dailystore.utils.isPasswordValid
import com.dailystore.utils.isUsernameValid
import com.dailystore.views.auth.AuthListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SignUpViewModel(private val authRepository: AuthRepository, private val usersRepository: UsersRepository) : ViewModel() {
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
            isPasswordValid(password.get()!!) and  isPasswordValid(confirmPassword.get()!!))) {
            authListener?.onFailure("Not all fields are valid")
            return
        }

        if(password.get()!! != confirmPassword.get()!!){
            authListener?.onFailure("Password mismatch")
            return
        }

        authListener?.onStarted()

        val disposable = authRepository.signUp(email.get()!!, password.get()!!)
            .andThen(usersRepository.create(User(user!!.uid, username.get()!!, email.get()!!)))
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