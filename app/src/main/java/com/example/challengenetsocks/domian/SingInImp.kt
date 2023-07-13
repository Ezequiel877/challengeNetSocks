package com.example.challengenetsocks.domian

import com.example.challengenetsocks.model.User
import com.example.challengenetsocks.model.UserAuth
import com.google.firebase.auth.FirebaseUser

class SingInImp(private val data: UserDataSource) : Repository {

    override suspend fun singUp(email: String, password: String): FirebaseUser? =
        data.checkUser(email, password)

    override suspend fun register(use: UserAuth): FirebaseUser? {
        TODO("Not yet implemented")
    }

}