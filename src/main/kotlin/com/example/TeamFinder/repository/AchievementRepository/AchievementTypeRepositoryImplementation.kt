package com.example.TeamFinder.repository.AchievementRepository

import com.example.TeamFinder.model.Achievement.AchievementType.AchievementTypeModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class AchievementTypeRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : AchievementTypeRepository {

    override fun getById(id: Int): AchievementTypeModel =
        jdbcTemplate.query(
            "select * from AchievementTypeTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first()

    override fun getByName(name: String): AchievementTypeModel =
        jdbcTemplate.query(
            "select * from AchievementTypeTable where name = :name",
            mapOf(
                "name" to name,
            ),
            ROW_MAPPER
        ).first()

    companion object {
        val ROW_MAPPER = RowMapper<AchievementTypeModel> { it, _ ->
            AchievementTypeModel(
                id = it.getInt("id"),
                value = it.getInt("value"),
                name = it.getString("name"),
            )
        }
    }
}