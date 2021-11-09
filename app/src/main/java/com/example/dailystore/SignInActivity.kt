package com.example.dailystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun onSignUpNavClick(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
        this.finish()
    }
}