package com.example.dailystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

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

        signUpBtn.setOnClickListener {
            checkUserInput()
        }

        signInNav.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun checkUserInput() {
        if (!isLoginValid(login.text.toString())) {
            Toast.makeText(applicationContext, "Login is invalid", Toast.LENGTH_SHORT).show()
        }else if (!isEmailValid(email.text.toString())){
            Toast.makeText(applicationContext, "Email is invalid", Toast.LENGTH_SHORT).show()
        }else if(password.text.toString() != confirmPassword.text.toString() || !isPasswordValid(password.text.toString())){
            Toast.makeText(applicationContext, "Password is invalid", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun isLoginValid(login: String): Boolean {
        return login.length > 3
    }

    private fun isEmailValid(email: String): Boolean {
        return email.length > 3
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 3
    }
}