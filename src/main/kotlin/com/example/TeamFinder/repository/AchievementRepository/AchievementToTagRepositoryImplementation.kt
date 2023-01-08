package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementToTagModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class AchievementToTagRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : AchievementToTagRepository {
    override fun getByAchievementId(achievementId: Int): AchievementToTagModel =
        jdbcTemplate.query(
            "select * from AchievementToTagTable where achievementId = :achievementId",
            mapOf(
                "achievementId" to achievementId,
            ),
            ROW_MAPPER,
        ).first()

    override fun setByAchievementIdAndTagId(achievementId: Int, tagId: Int) {
        jdbcTemplate.update(
            "insert into AchievementToTagTable (achievementId, tagId) values (:achievementId, tagId)",
            mapOf(
                "achievementId" to achievementId,
                "tagId" to tagId,
            ),
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<AchievementToTagModel> { it, _ ->
            AchievementToTagModel(
                achievementId = it.getInt("achievementId"),
                tagId = it.getInt("tagId"),
            )
        }
    }
}