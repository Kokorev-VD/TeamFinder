package com.example.TeamFinder.repository.UserRepository

import com.example.TeamFinder.model.User.UserAchievementsModel

interface UserAchievementsRepository {

    fun getByUserId(userId: Int): List<UserAchievementsModel>

    fun create(userId: Int, achievements: List<String>)

    fun deleteByAchievement(userId: Int, achievement: String)

    fun deleteByUserId(userId: Int)

    fun update(userId: Int, newAchievementsList: List<String>)
}