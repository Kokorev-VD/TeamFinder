package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementToTypeModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AchievementToTypeRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : AchievementToTypeRepository {
    override fun getByAchievementId(achievementId: Int): AchievementToTypeModel =
        jdbcTemplate.query(
            "select * from AchievementToTypeTable where achievementId = :achievementId",
            mapOf(
                "achievementId" to achievementId,
            ),
            ROW_MAPPER,
        ).first()

    override fun setByAchievementIdAndTypeId(achievementId: Int, typeId: Int) {
        jdbcTemplate.update(
            "insert into AchievementToTypeTable (achievementId, typeId) values (:achievementId, :typeId)",
            mapOf(
                "achievementId" to achievementId,
                "typeId" to typeId,
            )
        )
    }

    override fun deleteByAchievementId(achievementId: Int) {
        jdbcTemplate.update(
            "delete from AchievementToTypeTable where achievementId = :achievementId",
            mapOf(
                "achievementId" to achievementId,
            )
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<AchievementToTypeModel> { it, _ ->
            AchievementToTypeModel(
                achievementId = it.getInt("achievementId"),
                typeId = it.getInt("typeId"),
            )
        }
    }
}