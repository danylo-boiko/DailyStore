package com.example.dailystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton

class SignInActivity : AppCompatActivity() {
    private lateinit var usernameOrEmail: EditText
    private lateinit var password: EditText
    private lateinit var signInButton: CircularProgressButton
    private lateinit var restorePassword: TextView

    private lateinit var tvSignUpNav: TextView
    private lateinit var ivSignUpNav: ImageView

    private lateinit var facebookAuth: ImageView
    private lateinit var googleAuth: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        usernameOrEmail = findViewById(R.id.editTextUsernameOrEmail)
        password = findViewById(R.id.editTextPassword)
        signInButton = findViewById(R.id.buttonSignIn)
        restorePassword = findViewById(R.id.textViewRestorePassword)

        tvSignUpNav = findViewById(R.id.textViewSignUpNav)
        ivSignUpNav = findViewById(R.id.imageViewSignUpNav)

        facebookAuth = findViewById(R.id.imageViewFacebookAuth)
        googleAuth = findViewById(R.id.imageViewGoogleAuth)

        tvSignUpNav.setOnClickListener(signUpNav)
        ivSignUpNav.setOnClickListener(signUpNav)

        signInButton.setOnClickListener {
            Toast.makeText(applicationContext, "TODO Sign In", Toast.LENGTH_SHORT).show()
        }

        restorePassword.setOnClickListener {
            Toast.makeText(applicationContext, "TODO restore password", Toast.LENGTH_SHORT).show()
        }

        facebookAuth.setOnClickListener {
            Toast.makeText(applicationContext, "TODO Facebook Auth", Toast.LENGTH_SHORT).show()
        }

        googleAuth.setOnClickListener {
            Toast.makeText(applicationContext, "TODO Google Auth", Toast.LENGTH_SHORT).show()
        }
    }

    private val signUpNav: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, SignUpActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
        this.finish()
    }
}