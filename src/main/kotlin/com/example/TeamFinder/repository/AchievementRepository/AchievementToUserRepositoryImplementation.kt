package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementToUserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class AchievementToUserRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : AchievementToUserRepository {

    override fun getByUserId(userId: Int): AchievementToUserModel =
        jdbcTemplate.query(
            "select * from AchievementToUserTable where userId = :userId",
            mapOf(
                "userId" to userId,
            ),
            ROW_MAPPER
        ).first()

    override fun setByAchievementIdAndUserId(achiementId: Int, userId: Int) {
        jdbcTemplate.update(
            "insert into AchievementToUserTable (achievementId, userId) values (:achievementId, :userId)",
            mapOf(
                "achievementId" to achiementId,
                "userId" to userId,
            ),
        )
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