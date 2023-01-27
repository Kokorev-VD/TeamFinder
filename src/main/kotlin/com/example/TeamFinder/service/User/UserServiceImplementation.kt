package com.example.TeamFinder.service.User

import com.example.TeamFinder.dto.Mark.MarkWithPost
import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.User
import com.example.TeamFinder.dto.User.UserAchievement
import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel
import com.example.TeamFinder.repository.AchievementRepository.*
import com.example.TeamFinder.repository.CityRepository.CityRepository
import com.example.TeamFinder.repository.CityToUserRepository.CityToUserRepository
import com.example.TeamFinder.repository.JobRepository.JobRepository
import com.example.TeamFinder.repository.JobToUserRepository.JobToUserRepository
import com.example.TeamFinder.repository.MarkRepository.MarkRepository
import com.example.TeamFinder.repository.TagToUserRepository.TagToUserRepository
import com.example.TeamFinder.repository.TeamRepository.TeamRepository
import com.example.TeamFinder.repository.UserCreatorToPostRepository.UserCreatorToPostRepository
import com.example.TeamFinder.repository.UserRepository.UserLoginParamsRepository
import com.example.TeamFinder.repository.UserRepository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service("User")
class UserServiceImplementation(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userLoginParamsRepository: UserLoginParamsRepository,
    @Autowired private val tagToUserRepository: TagToUserRepository,
    @Autowired private val markRepository: MarkRepository,
    @Autowired private val userCreatorToPostRepository: UserCreatorToPostRepository,
    @Autowired private val teamRepository: TeamRepository,
    @Autowired private val achievementToUserRepository: AchievementToUserRepository,
    @Autowired private val achievementRepository: AchievementRepository,
    @Autowired private val achievementToTagRepository: AchievementToTagRepository,
    @Autowired private val achievementToTypeRepository: AchievementToTypeRepository,
    @Autowired private val achievementTypeRepository: AchievementTypeRepository,
    @Autowired private val jobToUserRepository: JobToUserRepository,
    @Autowired private val jobRepository: JobRepository,
    @Autowired private val cityToUserRepository: CityToUserRepository,
    @Autowired private val cityRepository: CityRepository,
): UserService {
    override fun getById(id: Int): User {
        val userModel = userRepository.findById(id)
        return User(
            login = userLoginParamsRepository.getById(id).login,
            tg = userModel.tg,
            description = userModel.description,
            imageId = userModel.imageId,
            email = userModel.email,
        )
    }

    override fun getUserAchievement(id: Int): List<Int> {
        val res = mutableListOf<Int>()
        for (i in achievementToUserRepository.getByUserId(id)) {
            res.add(i.achievementId)
        }
        return res
    }

    override fun getUserTags(id: Int): List<String> =
        tagToUserRepository.getStringTagsByUserId(id)

    override fun getUserTeam(id: Int): List<Int> {
        val res = mutableListOf<Int>()
        for (i in teamRepository.getByUserId(id)) {
            res.add(i.teamId)
        }
        return res
    }

    override fun getUserMarks(id: Int): List<MarkWithPost> =
        markRepository.getMarkWithStringPostByUserId(id)

    override fun getAchievementTypes(): List<String> {
        val res = mutableListOf<String>()
        for (i in achievementTypeRepository.getAll()) {
            res.add(i.name)
        }
        return res
    }

    override fun getUserJob(id: Int): List<String> {
        val res = mutableListOf<String>()
        for (i in jobToUserRepository.getJobByUserId(id)) {
            res.add(jobRepository.findJobById(i.jobId))
        }
        return res
    }

    override fun getUserCity(id: Int): String =
        cityToUserRepository.getCityByUserId(id)

    override fun setUserJob(id: Int, job: List<String>) {
        jobToUserRepository.update(id, job)
    }

    override fun setUserCity(id: Int, cityName: String) {
        cityToUserRepository.update(id, cityName)
    }


    override fun getUserPost(id: Int): List<Int> {
        val res = mutableListOf<Int>()
        for (i in userCreatorToPostRepository.getUserCreatorToPostModelByUserId(id)) {
            res.add(i.postId)
        }
        return res
    }

    override fun setUserAchievement(userAchievement: UserAchievement) {
        val achievementId = achievementRepository.setAchievement(userAchievement.achievementTitle)
        achievementToUserRepository.setByAchievementIdAndUserId(achievementId, userAchievement.userId)
        achievementToTagRepository.setByAchievementIdAndTagId(achievementId, userAchievement.achievementTag)
        achievementToTypeRepository.setByAchievementIdAndTypeId(achievementId, userAchievement.achievementTypeId)
    }

    override fun updateUserInfo(user: UserModel) {
        userRepository.update(user.id, user.tg, user.description, user.imageId, user.email)
    }

    override fun updateUserTag(id: Int, tag: List<String>) {
        tagToUserRepository.update(id, tag)
    }

    override fun authorisation(userLoginParams: UserLoginParamsModel): Response {
        val dataBaseUserLoginParamsModel =
            userLoginParamsRepository.getByLogin(userLoginParams.login) ?: return Response(600) // user not found
        if (dataBaseUserLoginParamsModel.pass == userLoginParams.pass) {
            return Response(200) // OK
        }
        return Response(601) // passwords don't equal
    }

    override fun registration(login: String, pass: String): Response {
        if (userLoginParamsRepository.getByLogin(login) == null) {
            userLoginParamsRepository.create(login, pass)
            return Response(200)
        }
        return Response(602)
    }
}