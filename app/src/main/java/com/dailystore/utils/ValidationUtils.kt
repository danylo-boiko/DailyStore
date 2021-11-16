package com.dailystore.utils

import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.dailystore.R
import com.google.android.material.textfield.TextInputLayout
import com.wajahatkarim3.easyvalidation.core.view_ktx.maxLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail


@BindingAdapter("emailValidator")
fun TextInputLayout.emailValidator(email: String?) {
    if (TextUtils.isEmpty(email)) {
        error = null
        return
    }

    if (!email!!.validEmail()) {
        error = resources.getString(R.string.invalid_email_format_message)
    } else {
        error = null
    }
}

@BindingAdapter("usernameValidator")
fun TextInputLayout.usernameValidator(username: String?) {
    val minLength = 3
    val maxLength = 32
    val usernameRegex = Regex("^[A-Za-z0-9_-]+$")

    if (TextUtils.isEmpty(username)) {
        error = null
        return
    }

    if (!username!!.matches(usernameRegex)) {
        error = resources.getString(R.string.invalid_username_regex_matches_message)
    } else if (!username.minLength(minLength)) {
        error = resources.getString(R.string.invalid_username_min_length_message)
    } else if (!username.maxLength(maxLength)) {
        error = resources.getString(R.string.invalid_username_max_length_message)
    } else {
        error = null
    }
}

@BindingAdapter("passwordValidator")
fun TextInputLayout.passwordValidator(password: String?) {
    val minLength = 8
    val maxLength = 100
    val usernameRegex = Regex("(?=.*?[0-9])(?=.*?[A-Za-z]).+")

    if (TextUtils.isEmpty(password)) {
        error = null
        return
    }

    if (!password!!.matches(usernameRegex)) {
        error = resources.getString(R.string.invalid_password_regex_matches_message)
    } else if (!password.minLength(minLength)) {
        error = resources.getString(R.string.invalid_password_min_length_message)
    } else if (!password.maxLength(maxLength)) {
        error = resources.getString(R.string.invalid_password_max_length_message)
    } else {
        error = null
    }
}