package com.dailystore.views.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dailystore.R
import com.dailystore.Settings
import com.dailystore.databinding.ActivitySignInBinding
import com.dailystore.viewmodels.auth.AuthViewModelFactory
import com.dailystore.viewmodels.auth.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class SignInActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    private val progressbar: ProgressBar by lazy {
        findViewById(R.id.progressbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initGoogleSignInClient()
        initButtons()
    }

    private fun initViewModel() {
        val binding: ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        signInViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)
        binding.viewModel = signInViewModel
        signInViewModel.authListener = this
    }

    private fun initGoogleSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun initButtons() {
        findViewById<ImageView>(R.id.imageViewGoogleAuth).setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, Settings.GOOGLE_RC_SIGN_IN)
        }

        val signUpNavClickListener = View.OnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
            this.finish()
        }

        findViewById<ImageView>(R.id.imageViewSignUpNav).setOnClickListener(signUpNavClickListener)
        findViewById<TextView>(R.id.textViewSignUpNav).setOnClickListener(signUpNavClickListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Settings.GOOGLE_RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                signInViewModel.signInWithGoogle(credential)
            } catch (e: ApiException) {
                Log.w("Google sign in error", e.message!!)
            }
        }
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