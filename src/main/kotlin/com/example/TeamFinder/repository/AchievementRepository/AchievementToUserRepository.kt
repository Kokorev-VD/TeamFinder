package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementToUserModel

interface AchievementToUserRepository {

    fun getByUserId(userId: Int): AchievementToUserModel

    fun setByAchievementIdAndUserId(achiementId: Int, userId: Int)

}