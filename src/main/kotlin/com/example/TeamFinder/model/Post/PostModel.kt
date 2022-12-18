package com.example.TeamFinder.model.Post

data class PostModel(
    val id: Int,
    var creatorId: Int,
    val title: String,
    val body: String,
)
