package com.example.TeamFinder.dto.User

import com.example.TeamFinder.dto.Mark.MarkWithPost

data class UserMark(
    val id: Int,
    val userMark: List<MarkWithPost>,
)
