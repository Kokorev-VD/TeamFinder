package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class AchievementRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : AchievementRepository {
    override fun getBuId(id: Int): AchievementModel =
        jdbcTemplate.query(
            "select * from AchievementTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first()

    override fun setByIdAndAchievement(id: Int, achievement: String) {
        jdbcTemplate.update(
            "insert into AchievementTable (id, achievement) values (:id, :achievement)",
            mapOf(
                "id" to id,
                "achievement" to achievement,
            )
        )
    }

    override fun getIdByAchievement(achievement: String): AchievementModel =
        jdbcTemplate.query(
            "select * from AchievementTable where achievement = :achievement",
            mapOf(
                "achievement" to achievement,
            ),
            ROW_MAPPER
        ).first()

    companion object {
        val ROW_MAPPER = RowMapper<AchievementModel> { it, _ ->
            AchievementModel(
                id = it.getInt("id"),
                achievement = it.getString("achievement")
            )
        }
    }
}