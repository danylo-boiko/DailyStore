package com.dailystore.repositories

import com.dailystore.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable

class AuthRepository {
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val usersReference: DatabaseReference by lazy {
        val path = "https://daily-store-309-default-rtdb.europe-west1.firebasedatabase.app"
        FirebaseDatabase.getInstance(path).getReference("Users")
    }

    fun signIn(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    emitter.onComplete()
                } else {
                    emitter.onError(it.exception!!)
                }
            }
        }
    }

    fun signUp(username: String, email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    val userId = firebaseAuth.currentUser!!.uid
                    val user = User(userId, username, email)

                    usersReference.child(userId).setValue(user).addOnCompleteListener {
                            if (!emitter.isDisposed) {
                                if (it.isSuccessful) {
                                    emitter.onComplete()
                                } else {
                                    emitter.onError(it.exception!!)
                                }
                            }
                        }
                } else {
                    emitter.onError(it.exception!!)
                }
            }
        }
    }

    fun resetPassword(email: String) = Completable.create { emitter ->
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    emitter.onComplete()
                } else {
                    emitter.onError(it.exception!!)
                }
            }
        }
    }

    fun signOut() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}