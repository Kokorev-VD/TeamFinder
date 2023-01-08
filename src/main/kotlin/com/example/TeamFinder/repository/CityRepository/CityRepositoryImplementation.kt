package com.example.TeamFinder.repository.CityRepository

import com.example.TeamFinder.model.City.CityModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class CityRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : CityRepository {
    override fun findLastId(): Int =
        jdbcTemplate.query(
            "select max(id) from CityTable",
            ROW_MAPPER
        ).first().id

    override fun createNewCity(name: String) {
        val id = findLastId()
        jdbcTemplate.update(
            "insert into CityTable (id, name) values (:id, :name)",
            mapOf(
                "id" to id,
                "name" to name,
            )
        )
    }

    override fun findCityById(id: Int): String =
        jdbcTemplate.query(
            "select * from CityTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first().name

    override fun findCityByName(name: String): Int {
        return if (jdbcTemplate.query("select * from CityTable where name = :name", mapOf("name" to name), ROW_MAPPER)
                .firstOrNull() == null
        ) {
            createNewCity(name)
            findLastId() - 1
        } else {
            jdbcTemplate.query("select * from CityTable where name = :name", mapOf("name" to name), ROW_MAPPER)
                .first().id
        }
    }

    companion object {
        val ROW_MAPPER = RowMapper<CityModel> { it, _ ->
            CityModel(
                id = it.getInt("id"),
                name = it.getString("name")
            )
        }
    }
}