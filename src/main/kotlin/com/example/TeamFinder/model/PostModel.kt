package com.example.TeamFinder.model

data class PostModel(
    val id: Int,
    var creator: Int,
    val header: String,
    val body: String,
    val pos_mark: Int,
    val neg_mark: Int,
)
