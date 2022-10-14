package com.example.TeamFinder.repository

import com.example.TeamFinder.model.UserModel
import dto.User

interface UserRepository {

    fun getAll():List<UserModel>

    fun findById(id: Int): UserModel?

    fun findByLogin(login: String): UserModel?

    fun findLastId(): UserModel?

    fun create(login:String, password: String): Int

    fun update(id: Int, user: User)

    fun deleteById(id: Int)

}