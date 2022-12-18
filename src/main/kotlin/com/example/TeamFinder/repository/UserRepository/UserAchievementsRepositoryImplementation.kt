package com.example.TeamFinder.repository.UserRepository

import com.example.TeamFinder.model.User.UserAchievementsModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class UserAchievementsRepositoryImplementation(
    val jdbcTemplate: NamedParameterJdbcTemplate,
) : UserAchievementsRepository {
    override fun getByUserId(id: Int): List<UserAchievementsModel> =
        jdbcTemplate.query(
            "select * from UserAchievementsTable where id = :id",
            mapOf(
                "id" to id
            ),
            ROW_MAPPER
        )

    override fun create(userId: Int, achievements: List<String>) {
        for (achievement in achievements) {
            jdbcTemplate.update(
                "insert into UserAchievementsTable (id, achievement) values (:userId, :achievement)",
                mapOf(
                    "userId" to userId,
                    "achievement" to achievement,
                )
            )
        }
    }

    override fun deleteByAchievement(userId: Int, achievement: String) {
        jdbcTemplate.update(
            "delete from UserAchievementsTable where achievement = :achievement and id = :userId",
            mapOf(
                "achievement" to achievement,
                "userId" to userId,
            )
        )
    }

    override fun deleteByUserId(userId: Int) {
        jdbcTemplate.update(
            "delete from userAchievementsTable where id = :userId ",
            mapOf(
                "userId" to userId
            )
        )
    }

    override fun update(userId: Int, newAchievementsList: List<String>) {
        deleteByUserId(userId)
        create(userId, newAchievementsList)
    }

    companion object {
        val ROW_MAPPER = RowMapper<UserAchievementsModel> { rs, _ ->
            UserAchievementsModel(
                id = rs.getInt("id"),
                achievement = rs.getString("achievement"),
            )
        }
    }

}