package com.example.challengenetsocks.domian

import com.example.challengenetsocks.model.UserAuth
import com.google.firebase.auth.FirebaseUser

interface Repository {
    suspend fun singUp(email:String, password:String): FirebaseUser?
    suspend fun register(use: UserAuth): FirebaseUser?

}