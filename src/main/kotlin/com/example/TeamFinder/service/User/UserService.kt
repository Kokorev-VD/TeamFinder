package com.example.TeamFinder.service.User

import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.*


interface UserService {

    fun getById(id: Int): User

    fun getUserAchievement(id: Int): UserAchievement

    fun getUserTags(id: Int): UserTag

    fun getUserTeam(id: Int): UserTeam

    fun getUserMark(id: Int): UserMark

    fun getAchievementTypes(): List<String>

    fun getUserJob(id: Int): UserJob

    fun getUserCity(id: Int): UserCity

    fun setUserJob(id: Int, job: List<String>)

    fun setUserCity(id: Int, cityName: String)

    fun getUserPost(id: Int): UserPost

    fun setUserAchievement(userAchievement: UserAchievement)

    fun updateUserInfo(id: Int, user: User)

    fun updateUserTag(id: Int, tag: List<String>)

    fun authorisation(userLoginParams: UserLoginParams): Response

    fun registration(login: String, pass: String): Response

}