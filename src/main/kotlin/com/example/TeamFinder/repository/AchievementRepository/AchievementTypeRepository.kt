package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementType.AchievementTypeModel

interface AchievementTypeRepository {

    fun getById(id: Int): AchievementTypeModel

    fun getByName(name: String): AchievementTypeModel

    fun getAll(): List<AchievementTypeModel>
}