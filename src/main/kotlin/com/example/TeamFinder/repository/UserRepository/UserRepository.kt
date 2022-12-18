package com.example.TeamFinder.repository.UserRepository

import com.example.TeamFinder.model.User.UserModel

interface UserRepository {

    fun findById(id: Int): UserModel

    fun create(
        tg: String,
        description: String,
        imageId: Int,
    ): Int

    fun update(id: Int, tg: String, description: String, imageId: Int)

}