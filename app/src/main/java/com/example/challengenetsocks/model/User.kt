package com.example.challengenetsocks.model

data class User(
    val email: String,
    var name: String = ""
)
data class UserAuth(
    val email: String,
    var name: String = "",
    var phone: String = "",
    var date: String = ""
)
