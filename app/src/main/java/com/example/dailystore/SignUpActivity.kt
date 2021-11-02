package com.example.dailystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {
    private lateinit var login: EditText;
    private lateinit var email: EditText;
    private lateinit var password: EditText;
    private lateinit var confirmPassword: EditText;
    private lateinit var signUpBtn: Button;
    private lateinit var signInNav: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        login = findViewById(R.id.login)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        signUpBtn = findViewById(R.id.signUpBtn)
        signInNav = findViewById(R.id.signInNav)

        signInNav.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}