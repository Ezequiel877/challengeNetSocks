package com.example.challengenetsocks.domian

import com.google.firebase.auth.FirebaseUser

interface Repository {
    suspend fun singUp(email:String, password:String): FirebaseUser?

}