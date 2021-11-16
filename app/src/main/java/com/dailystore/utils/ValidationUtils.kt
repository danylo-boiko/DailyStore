package com.dailystore.utils

import android.text.TextUtils
import androidx.databinding.BindingAdapter
import com.dailystore.R
import com.google.android.material.textfield.TextInputLayout
import com.wajahatkarim3.easyvalidation.core.view_ktx.maxLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail

const val minUsernameLength = 3
const val maxUsernameLength = 32
val usernameRegex = Regex("^[A-Za-z0-9_-]+$")

const val minPasswordLength = 8
const val maxPasswordLength = 100
val passwordRegex = Regex("(?=.*?[0-9])(?=.*?[A-Za-z]).+")


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
    if (TextUtils.isEmpty(username)) {
        error = null
        return
    }

    if (!username!!.matches(usernameRegex)) {
        error = resources.getString(R.string.invalid_username_regex_matches_message)
    } else if (!username.minLength(minUsernameLength)) {
        error = resources.getString(R.string.invalid_username_min_length_message)
    } else if (!username.maxLength(maxUsernameLength)) {
        error = resources.getString(R.string.invalid_username_max_length_message)
    } else {
        error = null
    }
}

@BindingAdapter("passwordValidator")
fun TextInputLayout.passwordValidator(password: String?) {
    if (TextUtils.isEmpty(password)) {
        error = null
        return
    }

    if (!password!!.matches(passwordRegex)) {
        error = resources.getString(R.string.invalid_password_regex_matches_message)
    } else if (!password.minLength(minPasswordLength)) {
        error = resources.getString(R.string.invalid_password_min_length_message)
    } else if (!password.maxLength(maxPasswordLength)) {
        error = resources.getString(R.string.invalid_password_max_length_message)
    } else {
        error = null
    }
}

fun isEmailValid(email: String): Boolean {
    return email.validEmail()
}

fun isUsernameValid(username: String): Boolean {
    return username.matches(usernameRegex) and username.minLength(minUsernameLength) and username.maxLength(maxUsernameLength)
}

fun isPasswordValid(password: String): Boolean {
    return password.matches(passwordRegex) and password.minLength(minPasswordLength) and password.maxLength(maxPasswordLength)
}