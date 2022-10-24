package com.example.TeamFinder.repository

import com.example.TeamFinder.model.UserModel

interface UserRepository {

    fun getAll():List<UserModel>

    fun findById(id: Int): UserModel?

    fun findByLogin(login: String): UserModel?

    fun findLastId(): UserModel?

    fun create(login: String, password: String, tg: String, description: String, role: String, imageId: Int): Int

    fun update(id: Int, login: String, tg: String, description: String, imageId: Int)

    fun deleteById(id: Int)

    fun userParameterMapper(
        login: String,
        password: String,
        tg: String,
        description: String,
        role: String,
        imageId: Int
    ): Map<String, Any>

}