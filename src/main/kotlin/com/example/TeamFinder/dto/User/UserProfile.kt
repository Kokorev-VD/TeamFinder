package com.example.TeamFinder.dto.User

data class UserProfile(
    val login: String,
    val tg: String,
    val description: String,
    val imageId: Int,
    val achievements: List<String>,
    val tags: List<String>,
)
