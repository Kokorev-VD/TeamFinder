package com.example.TeamFinder.dto.Post

import com.example.TeamFinder.dto.Tag.Tag

data class PostTag(
    val postId: Int,
    val tagList: List<Tag>,
)
