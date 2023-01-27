package com.example.TeamFinder.service.Recommendation

import com.example.TeamFinder.model.TagToPost.TagToPostModel
import com.example.TeamFinder.model.TagToUser.TagToUserModel
import com.example.TeamFinder.repository.AchievementRepository.AchievementToTagRepository
import com.example.TeamFinder.repository.AchievementRepository.AchievementToTypeRepository
import com.example.TeamFinder.repository.AchievementRepository.AchievementToUserRepository
import com.example.TeamFinder.repository.AchievementRepository.AchievementTypeRepository
import com.example.TeamFinder.repository.CityToUserRepository.CityToUserRepository
import com.example.TeamFinder.repository.JobToUserRepository.JobToUserRepository
import com.example.TeamFinder.repository.PostRepository.PostRepository
import com.example.TeamFinder.repository.TagToPostRepository.TagToPostRepository
import com.example.TeamFinder.repository.TagToUserRepository.TagToUserRepository
import com.example.TeamFinder.repository.UserCreatorToPostRepository.UserCreatorToPostRepository
import com.example.TeamFinder.repository.UserRepository.UserLoginParamsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecommendationServiceImplementation(
    @Autowired val cityToUserRepository: CityToUserRepository,
    @Autowired val jobToUserRepository: JobToUserRepository,
    @Autowired val achievementToUserRepository: AchievementToUserRepository,
    @Autowired val achievementToTypeRepository: AchievementToTypeRepository,
    @Autowired val achievementToTagRepository: AchievementToTagRepository,
    @Autowired val achievementTypeRepository: AchievementTypeRepository,
    @Autowired val userCreatorToPostRepository: UserCreatorToPostRepository,
    @Autowired val tagToPostRepository: TagToPostRepository,
    @Autowired val userLoginParamsRepository: UserLoginParamsRepository,
    @Autowired val tagToUserRepository: TagToUserRepository,
    @Autowired val postRepository: PostRepository,
) : RecommendationService {

    override fun calculateUserRankByPostId(userId: Int, postId: Int): Int {
        val postTagId = mutableListOf<Int>()
        for (i in tagToPostRepository.getTagsByPostId(postId)) postTagId.add(i.tagId)
        val postCreatorId = userCreatorToPostRepository.getUserCreatorToPostModelByPostId(postId).userId
        var res = 0
        if (cityToUserRepository.getCityByUserId(userId) == cityToUserRepository.getCityByUserId(postCreatorId)) {
            res += 2
        }
        val userJob = mutableListOf<Int>()
        for (i in jobToUserRepository.getJobByUserId(userId)) {
            userJob.add(i.jobId)
        }
        val creatorJob = mutableListOf<Int>()
        for (i in jobToUserRepository.getJobByUserId(postCreatorId)) {
            creatorJob.add(i.jobId)
        }
        for (i in userJob) {
            if (i in creatorJob) {
                res += 3
            }
        }
        for (i in achievementToUserRepository.getByUserId(userId)) {
            if (achievementToTagRepository.getByAchievementId(i.achievementId).tagId in postTagId) {
                res += achievementTypeRepository.getById(achievementToTypeRepository.getByAchievementId(i.achievementId).typeId).value
            }
        }
        return res
    }

    override fun calculatePostRankByUserId(userId: Int, postId: Int): Int {
        val postTagId = mutableListOf<Int>()
        for (i in tagToPostRepository.getTagsByPostId(postId)) postTagId.add(i.tagId)
        val postCreatorId = userCreatorToPostRepository.getUserCreatorToPostModelByPostId(postId).userId
        var res = 0
        if (cityToUserRepository.getCityByUserId(userId) == cityToUserRepository.getCityByUserId(postCreatorId)) {
            res += 2
        }
        val userJob = mutableListOf<Int>()
        for (i in jobToUserRepository.getJobByUserId(userId)) {
            userJob.add(i.jobId)
        }
        val creatorJob = mutableListOf<Int>()
        for (i in jobToUserRepository.getJobByUserId(postCreatorId)) {
            creatorJob.add(i.jobId)
        }
        for (i in userJob) {
            if (i in creatorJob) {
                res += 3
            }
        }
        for (i in achievementToUserRepository.getByUserId(postCreatorId)) {
            if (achievementToTagRepository.getByAchievementId(i.achievementId).tagId in postTagId) {
                res += achievementTypeRepository.getById(achievementToTypeRepository.getByAchievementId(i.achievementId).typeId).value
            }
        }
        return res
    }

    override fun recommendedPostIdByUserId(userId: Int): List<Int> {
        val userTagId = tagToUserRepository.getTagsByUserId(userId)
        val postIdToRecommendationPointsMap = mutableMapOf<Int, Int>()
        for (i in postRepository.getAllPost()) {
            if (containsElement(tagToPostRepository.getTagsByPostId(i.id), userTagId)) {
                postIdToRecommendationPointsMap[i.id] = calculatePostRankByUserId(i.id, userId)
            }
        }
        return postIdToRecommendationPointsMap.toList().sortedBy { (k, v) -> v }.toMap().keys.toList()
    }

    override fun recommendedUserIdByPostId(postId: Int): List<Int> {
        val postTagId = tagToPostRepository.getTagsByPostId(postId)
        val userIdToRecommendationPointsMap = mutableMapOf<Int, Int>()
        for (i in userLoginParamsRepository.getAllUserLoginParams()) {
            if (containsElement(postTagId, tagToUserRepository.getTagsByUserId(i.id))) {
                userIdToRecommendationPointsMap[i.id] = calculateUserRankByPostId(i.id, postId)
            }
        }
        return userIdToRecommendationPointsMap.toList().sortedBy { (k, v) -> v }.toMap().keys.toList()
    }

    fun containsElement(postTagId: List<TagToPostModel>, listTagToUserModel: List<TagToUserModel>): Boolean {
        var flag = false
        for (i in postTagId) {
            for (j in listTagToUserModel) {
                if (i.tagId == j.tagId) {
                    flag = true
                    break
                }
            }
        }
        return flag
    }
}