package com.dailystore.repositories

import com.dailystore.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable

class UsersRepository {
    private val usersReference: DatabaseReference by lazy {
        val path = "https://daily-store-309-default-rtdb.europe-west1.firebasedatabase.app"
        FirebaseDatabase.getInstance(path).getReference("Users")
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