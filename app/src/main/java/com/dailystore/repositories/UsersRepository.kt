package com.dailystore.repositories

import com.dailystore.Settings
import com.dailystore.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable

class UsersRepository {
    private val usersReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance(Settings.FIREBASE_DB_PATH).getReference(Settings.USERS_REFERENCE)
    }

    fun create(user: User) = Completable.create { emitter ->
        if (!emitter.isDisposed) {
            usersReference.child(user.id!!).setValue(user).addOnCompleteListener {
                if (!emitter.isDisposed) {
                    if (it.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(it.exception!!)
                    }
                }
            }
        }
    }
}