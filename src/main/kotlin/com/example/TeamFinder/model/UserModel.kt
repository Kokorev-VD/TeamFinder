package com.example.TeamFinder.model

data class UserModel(
    val id: Int = 0,
    val login: String,
    val password: String,
    val tg: String = "",
    val description: String = "",
    val role: String = "user",
    val imageId: Int = 0,
    )
