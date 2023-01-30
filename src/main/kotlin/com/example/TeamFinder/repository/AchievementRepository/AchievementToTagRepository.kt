package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementToTagModel

interface AchievementToTagRepository {

    fun getByAchievementId(achievementId: Int): AchievementToTagModel

    fun setByAchievementIdAndTagId(achievementId: Int, tagId: Int)

    fun deleteByAchievementId(achievementId: Int)
}