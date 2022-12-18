package com.example.TeamFinder.service.User

import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel


interface UserService {

    fun getById(id: Int): Any

    fun updateUserInfo(user: UserModel): Int

    fun updateUserTag(id: Int, tag: List<String>)

    fun updateUserAchievement(id: Int, achievement: List<String>)

    fun authorisation(userLoginParams: UserLoginParamsModel): Int

    fun registration(login: String, pass: String): Int

    fun setUserInfo(id: Int, tg: String, description: String, imageId: Int)

}