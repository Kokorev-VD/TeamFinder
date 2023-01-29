package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.dto.User.UserAchievement
import com.example.TeamFinder.model.Achievement.AchievementToUserModel

interface AchievementToUserRepository {

    fun getByUserId(userId: Int): List<AchievementToUserModel>

    fun setByAchievementIdAndUserId(achievementId: Int, userId: Int)

    fun deleteByUserId(userId: Int)

    fun update(userAchievement: UserAchievement)
}