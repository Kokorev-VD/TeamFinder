package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementToTypeModel

interface AchievementToTypeRepository {

    fun getByAchievementId(achievementId: Int): AchievementToTypeModel

    fun setByAchievementIdAndTypeId(achievementId: Int, typeId: Int)

    fun deleteByAchievementId(achievementId: Int)
}