package com.example.TeamFinder.repository.CityToUserRepository

import com.example.TeamFinder.model.CityToUser.CityToUserModel

interface CityToUserRepository {

    fun getCityByUserId(userId: Int): String

    fun getUserIdByCityId(cityId: Int): List<CityToUserModel>

    fun createByCityNameAndUserId(cityName: String, userId: Int)

    fun deleteByUserId(userId: Int)

    fun update(userId: Int, cityName: String)

}