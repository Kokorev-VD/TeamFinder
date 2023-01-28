package com.example.TeamFinder.dto.Post

import com.example.TeamFinder.dto.User.User

data class PostTeam(
    val postId: Int,
    val team: List<User>,
)
