package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.dto.User.UserAchievement
import com.example.TeamFinder.model.Achievement.AchievementToUserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AchievementToUserRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired val achievementRepository: AchievementRepository,
    @Autowired val achievementToTagRepository: AchievementToTagRepository,
    @Autowired val achievementToTypeRepository: AchievementToTypeRepository,
) : AchievementToUserRepository {

    override fun getByUserId(userId: Int): List<AchievementToUserModel> =
        jdbcTemplate.query(
            "select * from userToAchievementTable where userId = :userId",
            mapOf(
                "userId" to userId,
            ),
            ROW_MAPPER
        )

    override fun setByAchievementIdAndUserId(achievementId: Int, userId: Int) {
        jdbcTemplate.update(
            "insert into userToAchievementTable (achievementId, userId) values (:achievementId, :userId)",
            mapOf(
                "achievementId" to achievementId,
                "userId" to userId,
            ),
        )
    }

    override fun deleteByUserId(userId: Int) {
        jdbcTemplate.update(
            "delete from userToAchievementTable where userId = :userId",
            mapOf(
                "userId" to userId,
            )
        )
    }

    override fun update(userAchievement: UserAchievement) {
        val m = getByUserId(userAchievement.userId)
        deleteByUserId(userAchievement.userId)
        for (i in m) {
            println(i.toString())
            achievementToTagRepository.deleteByAchievementId(i.achievementId)
            achievementToTypeRepository.deleteByAchievementId(i.achievementId)
            achievementRepository.deleteById(i.achievementId)
        }
        for (achievement in userAchievement.achievement) {
            achievementRepository.setAchievement(achievement.achievementTitle)
            setByAchievementIdAndUserId(
                achievementRepository.getIdByAchievement(achievement.achievementTitle).id,
                userAchievement.userId
            )
        }
    }

    companion object {
        val ROW_MAPPER = RowMapper<AchievementToUserModel> { it, _ ->
            AchievementToUserModel(
                achievementId = it.getInt("achievementId"),
                userId = it.getInt("userId"),
            )
        }
    }
}