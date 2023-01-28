package com.example.TeamFinder.dto.User

import com.example.TeamFinder.dto.Achievement.Achievement

data class UserAchievement(
    val userId: Int,
    val achievement: List<Achievement>,
)
