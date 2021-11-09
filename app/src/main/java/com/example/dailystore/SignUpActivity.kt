package com.example.dailystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton


class SignUpActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var signUpButton: CircularProgressButton

    private lateinit var tvSignInNav: TextView
    private lateinit var ivSignInNav: ImageView

    private lateinit var facebookAuth: ImageView
    private lateinit var googleAuth: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        username = findViewById(R.id.editTextUsername)
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        confirmPassword = findViewById(R.id.editTextConfirmPassword)
        signUpButton = findViewById(R.id.buttonSignUp)


        tvSignInNav = findViewById(R.id.textViewSignInNav)
        ivSignInNav = findViewById(R.id.imageViewSignInNav)

        facebookAuth = findViewById(R.id.imageViewFacebookAuth)
        googleAuth = findViewById(R.id.imageViewGoogleAuth)

        tvSignInNav.setOnClickListener(signInNav)
        ivSignInNav.setOnClickListener(signInNav)

        signUpButton.setOnClickListener {
            Toast.makeText(applicationContext, "TODO Sign Up", Toast.LENGTH_SHORT).show()
        }

        facebookAuth.setOnClickListener {
            Toast.makeText(applicationContext, "TODO Facebook Auth", Toast.LENGTH_SHORT).show()
        }

        googleAuth.setOnClickListener {
            Toast.makeText(applicationContext, "TODO Google Auth", Toast.LENGTH_SHORT).show()
        }
    }

    private val signInNav: View.OnClickListener = View.OnClickListener {
        startActivity(Intent(this, SignInActivity::class.java))
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right)
        this.finish()
    }
}