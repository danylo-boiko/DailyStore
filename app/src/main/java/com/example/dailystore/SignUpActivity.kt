package com.example.dailystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun onSignInNavClick(view: View) {
        startActivity(Intent(this, SignInActivity::class.java))
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right)
        this.finish()
    }
}