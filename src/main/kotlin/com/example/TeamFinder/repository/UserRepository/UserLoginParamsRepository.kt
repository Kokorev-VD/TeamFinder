package com.example.TeamFinder.repository.UserRepository

import com.example.TeamFinder.model.User.UserLoginParamsModel

interface UserLoginParamsRepository {

    fun getById(id: Int): UserLoginParamsModel

    fun getLastId(): Int

    fun getByLogin(login: String): UserLoginParamsModel?

    fun create(userLoginParamsModel: UserLoginParamsModel)
}