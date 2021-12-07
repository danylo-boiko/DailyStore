package com.dailystore.views.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dailystore.R
import com.dailystore.databinding.ActivitySignUpBinding
import com.dailystore.viewmodels.auth.AuthViewModelFactory
import com.dailystore.viewmodels.auth.SignUpViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class SignUpActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var signUpViewModel: SignUpViewModel

    private val progressbar: ProgressBar by lazy {
        findViewById(R.id.progressbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initButtons()
    }

    private fun initViewModel() {
        val binding: ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel = ViewModelProvider(this, factory).get(SignUpViewModel::class.java)
        binding.viewModel = signUpViewModel

        signUpViewModel.authListener = this
    }

    private fun initButtons() {
        val signInNavClickListener = View.OnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right)
            this.finish()
        }

        findViewById<ImageView>(R.id.imageViewSignInNav).setOnClickListener(signInNavClickListener)
        findViewById<TextView>(R.id.textViewSignInNav).setOnClickListener(signInNavClickListener)
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progressbar.visibility = View.GONE
        Toast.makeText(this, signUpViewModel.user?.email, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}