package com.example.TeamFinder.service.User

import com.example.TeamFinder.dto.Achievement.Achievement
import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.*
import com.example.TeamFinder.repository.AchievementRepository.*
import com.example.TeamFinder.repository.CityToUserRepository.CityToUserRepository
import com.example.TeamFinder.repository.JobRepository.JobRepository
import com.example.TeamFinder.repository.JobToUserRepository.JobToUserRepository
import com.example.TeamFinder.repository.MarkRepository.MarkRepository
import com.example.TeamFinder.repository.TagRepository.TagRepository
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
    @Autowired private val tagRepository: TagRepository,
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

    override fun getUserAchievement(id: Int): UserAchievement {
        val res = mutableListOf<Achievement>()
        for (i in achievementToUserRepository.getByUserId(id)) {
            res.add(
                Achievement(
                    achievementRepository.getById(i.achievementId).achievement,
                    achievementTypeRepository.getById(achievementToTypeRepository.getByAchievementId(i.achievementId).typeId).name,
                    tagRepository.getById(achievementToTagRepository.getByAchievementId(i.achievementId).tagId).title
                )
            )
        }
        return UserAchievement(id, res)
    }

    override fun getUserTags(id: Int): UserTag =
        UserTag(id, tagToUserRepository.getStringTagsByUserId(id))

    override fun getUserTeam(id: Int): UserTeam {
        val res = mutableListOf<Int>()
        for (i in teamRepository.getByUserId(id)) {
            res.add(i.teamId)
        }
        return UserTeam(id, res)
    }

    override fun getUserMark(id: Int): UserMark =
        UserMark(id, markRepository.getMarkWithStringPostByUserId(id))

    override fun getAchievementTypes(): List<String> {
        val res = mutableListOf<String>()
        for (i in achievementTypeRepository.getAll()) {
            res.add(i.name)
        }
        return res
    }

    override fun getUserJob(id: Int): UserJob {
        val res = mutableListOf<String>()
        for (i in jobToUserRepository.getJobByUserId(id)) {
            res.add(jobRepository.findJobById(i.jobId))
        }
        return UserJob(id, res)
    }

    override fun getUserCity(id: Int): UserCity =
        UserCity(id, cityToUserRepository.getCityByUserId(id))

    override fun setUserJob(id: Int, job: List<String>) {
        jobToUserRepository.update(id, job)
    }

    override fun setUserCity(id: Int, cityName: String) {
        cityToUserRepository.update(id, cityName)
    }


    override fun getUserPost(id: Int): UserPost {
        val res = mutableListOf<Int>()
        for (i in userCreatorToPostRepository.getUserCreatorToPostModelByUserId(id)) {
            res.add(i.postId)
        }
        return UserPost(id, res)
    }

    override fun setUserAchievement(userAchievement: UserAchievement) {
        achievementToUserRepository.update(userAchievement)
        for (achievement in userAchievement.achievement) {
            val achievementId = achievementRepository.getIdByAchievement(achievement.achievementTitle).id
            achievementToTagRepository.setByAchievementIdAndTagId(
                achievementId,
                tagRepository.getByTitile(achievement.achievementTag).id
            )
            achievementToTypeRepository.setByAchievementIdAndTypeId(
                achievementId,
                achievementTypeRepository.getByName(achievement.achievementType).id
            )
        }
    }

    override fun updateUserInfo(id: Int, user: User) {
        userRepository.update(id, user.tg, user.description, user.imageId, user.email)
    }

    override fun updateUserTag(id: Int, tag: List<String>) {
        tagToUserRepository.update(id, tag)
    }

    override fun authorisation(userLoginParams: UserLoginParams): Response {
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