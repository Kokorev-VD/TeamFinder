package com.example.TeamFinder.service.User

import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.UserProfile
import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel


interface UserService {

    fun getById(id: Int): UserProfile

    fun updateUserInfo(user: UserModel): Response

    fun updateUserTag(id: Int, tag: List<String>)

    fun updateUserAchievement(id: Int, achievement: List<String>)

    fun authorisation(userLoginParams: UserLoginParamsModel): Response

    fun registration(login: String, pass: String): Any

    fun setUserInfo(id: Int, tg: String, description: String, imageId: Int)

}