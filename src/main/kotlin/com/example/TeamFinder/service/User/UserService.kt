package com.example.TeamFinder.service.User

import com.example.TeamFinder.dto.Mark.MarkWithPost
import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.User
import com.example.TeamFinder.dto.User.UserAchievement
import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel


interface UserService {

    fun getById(id: Int): User

    fun getUserAchievement(id: Int): List<Int>

    fun getUserTags(id: Int): List<String>

    fun getUserTeam(id: Int): List<Int>

    fun getUserMarks(id: Int): List<MarkWithPost>

    fun getAchievementTypes(): List<String>

    fun getUserJob(id: Int): List<String>

    fun getUserCity(id: Int): String

    fun setUserJob(id: Int, job: List<String>)

    fun setUserCity(id: Int, cityName: String)

    fun getUserPost(id: Int): List<Int>

    fun setUserAchievement(userAchievement: UserAchievement)

    fun updateUserInfo(user: UserModel)

    fun updateUserTag(id: Int, tag: List<String>)

    fun authorisation(userLoginParams: UserLoginParamsModel): Response

    fun registration(login: String, pass: String): Any

}