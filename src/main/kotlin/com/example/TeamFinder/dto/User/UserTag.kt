package com.example.TeamFinder.dto.User

import com.example.TeamFinder.dto.Tag.Tag

data class UserTag(
    val id: Int,
    val tag: List<Tag>,
)
