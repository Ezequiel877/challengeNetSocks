package com.example.challengenetsocks.domian

import com.example.challengenetsocks.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserDataSource {

    suspend fun checkUser(email: String, password: String): FirebaseUser? {
        val passWork = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return passWork.user
    }

    suspend fun chetAndRegistro(email: String, password: String, client: String, ): FirebaseUser? {
        val pass = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        pass.user?.uid?.let {
            FirebaseFirestore.getInstance().collection("user").document(it)
                .set(User(email, client, " username")).await()
        }
        return pass.user

    }

}
