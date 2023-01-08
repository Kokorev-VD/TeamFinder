package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementModel

interface AchievementRepository {

    fun getBuId(id: Int): AchievementModel

    fun setByIdAndAchievement(id: Int, achievement: String)

    fun getIdByAchievement(achievement: String): AchievementModel

}