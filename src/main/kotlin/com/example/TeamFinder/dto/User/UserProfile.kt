package com.example.TeamFinder.dto.User

import com.example.TeamFinder.dto.Mark.MarkWithStringPost

data class UserProfile(
    val login: String,
    val tg: String,
    val description: String,
    val imageId: Int,
    val achievements: List<String>,
    val tags: List<String>,
    val marks: List<MarkWithStringPost>,
)
