package com.example.TeamFinder.repository.CityRepository

interface CityRepository {

    fun findLastId(): Int

    fun createNewCity(name: String)

    fun findCityById(id: Int): String

    fun findCityByName(name: String): Int

}