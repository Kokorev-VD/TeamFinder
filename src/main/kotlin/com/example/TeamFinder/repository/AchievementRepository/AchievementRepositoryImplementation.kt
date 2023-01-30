package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AchievementRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : AchievementRepository {
    override fun getById(id: Int): AchievementModel =
        jdbcTemplate.query(
            "select * from AchievementTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first()

    fun getLastId(): Int =
        jdbcTemplate.query(
            "select * from achievementTable where id = (select max(id) from achievementTable)",
            ROW_MAPPER
        ).first().id + 1

    override fun setAchievement(achievement: String): Int {
        jdbcTemplate.update(
            "insert into AchievementTable (id, achievement) values (:id, :achievement)",
            mapOf(
                "id" to getLastId(),
                "achievement" to achievement,
            )
        )
        return getLastId() - 1
    }

    override fun getIdByAchievement(achievement: String): AchievementModel =
        jdbcTemplate.query(
            "select * from AchievementTable where achievement = :achievement",
            mapOf(
                "achievement" to achievement,
            ),
            ROW_MAPPER
        ).first()

    override fun deleteById(id: Int) {
        jdbcTemplate.update(
            "delete from achievementTable where id = :id",
            mapOf(
                "id" to id,
            )
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<AchievementModel> { it, _ ->
            AchievementModel(
                id = it.getInt("id"),
                achievement = it.getString("achievement")
            )
        }
    }
}