package com.example.TeamFinder.dto.Achievement

import com.example.TeamFinder.dto.Tag.Tag

data class Achievement(
    val achievementTitle: String,
    val achievementType: String,
    val achievementTag: Tag,
)
