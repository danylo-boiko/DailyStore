package com.dailystore.views.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dailystore.R
import com.dailystore.databinding.ActivitySignInBinding
import com.dailystore.viewmodels.auth.AuthViewModelFactory
import com.dailystore.viewmodels.auth.SignInViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignInActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var progressbar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        signInViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)
        binding.viewModel = signInViewModel

        signInViewModel.authListener = this

        progressbar = findViewById(R.id.progressbar)
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        Toast.makeText(this, signInViewModel.user?.email, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        signInViewModel.user?.let {
            Toast.makeText(this, signInViewModel.user?.email, Toast.LENGTH_SHORT).show()
        }
    }
}