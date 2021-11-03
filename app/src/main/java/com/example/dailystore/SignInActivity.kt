package com.example.dailystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    private lateinit var loginOrEmail: EditText;
    private lateinit var password: EditText;
    private lateinit var signInBtn: Button;
    private lateinit var signUpNav: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        loginOrEmail = findViewById(R.id.loginOrEmail)
        password = findViewById(R.id.password)
        signInBtn = findViewById(R.id.signInBtn)
        signUpNav = findViewById(R.id.signUpNav)

        signInBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        signUpNav.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}