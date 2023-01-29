package com.example.TeamFinder.dto.Post

data class Post(
    val id: Int,
    val title: String,
    val icon: Int,
    val description: String,
    val creatorLogin: String,
    val body: String,
    val team: List<Int>,
    val posMark: Int,
    val negMark: Int,
    val tagList: List<String>,
    val derivedPosts: List<MainInfoPost>,
    val basedPosts: List<MainInfoPost>,
)
