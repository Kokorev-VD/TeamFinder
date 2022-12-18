package com.example.TeamFinder.service.User

import com.example.TeamFinder.dto.User.UserProfile
import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel
import com.example.TeamFinder.repository.TagToUserRepository.TagToUserRepository
import com.example.TeamFinder.repository.UserRepository.UserAchievementsRepository
import com.example.TeamFinder.repository.UserRepository.UserLoginParamsRepository
import com.example.TeamFinder.repository.UserRepository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service("User")
class UserServiceImplementation(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userLoginParamsRepository: UserLoginParamsRepository,
    @Autowired private val userAchievementsRepository: UserAchievementsRepository,
    @Autowired private val tagToUserRepository: TagToUserRepository,
): UserService {
    override fun getById(id: Int): Any {
        val user = userRepository.findById(id)
        val userAchievements = mutableListOf<String>()
        for (i in userAchievementsRepository.getByUserId(id)) {
            userAchievements.add(i.achievement)
        }
        return UserProfile(
            login = userLoginParamsRepository.getById(id).login,
            tg = user.tg,
            description = user.description,
            imageId = user.imageId,
            achievements = userAchievements,
            tags = tagToUserRepository.getStringTagsByUserId(id).tag,
        )
    }

    override fun updateUserInfo(user: UserModel): Int {
        userRepository.update(user.id, user.tg, user.description, user.imageId)
        return 100
    }

    override fun updateUserTag(id: Int, tag: List<String>) {
        tagToUserRepository.update(id, tag)
    }

    override fun updateUserAchievement(id: Int, achievement: List<String>) {
        userAchievementsRepository.update(id, achievement)
    }

    override fun authorisation(userLoginParams: UserLoginParamsModel): Int {
        val dataBaseUserLoginParamsModel =
            userLoginParamsRepository.getByLogin(userLoginParams.login) ?: return 600 // user not found
        if (dataBaseUserLoginParamsModel.pass == userLoginParams.pass) {
            return 200 // OK
        }
        return 601 // passwords don't equal
    }

    override fun registration(login: String, pass: String): Int {
        TODO("Not yet implemented")
    }

    override fun setUserInfo(id: Int, tg: String, description: String, imageId: Int) {
        TODO("Not yet implemented")
    }
}