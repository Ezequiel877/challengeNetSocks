package com.example.challengenetsocks.domian

import com.google.firebase.auth.FirebaseUser

class SingInImp(private val data: UserDataSource) : Repository {

    override suspend fun singUp(email: String, password: String): FirebaseUser? =
        data.checkUser(email, password)

}