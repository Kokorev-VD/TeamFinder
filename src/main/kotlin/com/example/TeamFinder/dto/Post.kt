package com.example.TeamFinder.dto

data class Post(
    val id: Int,
    val creator: String,
    val header: String,
    val body: String,
    val pos_mark: Int,
    val neg_mark: Int,
)
