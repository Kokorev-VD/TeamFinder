package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementModel

interface AchievementRepository {

    fun getById(id: Int): AchievementModel

    fun setAchievement(achievement: String): Int

    fun getIdByAchievement(achievement: String): AchievementModel

    fun deleteById(id: Int)
}