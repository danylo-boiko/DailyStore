package com.dailystore.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val id: String? = null, val username: String? = null, val email: String? = null)
