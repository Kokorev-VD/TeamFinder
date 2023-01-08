package com.example.TeamFinder.repository.CityToUserRepository

import com.example.TeamFinder.model.CityToUser.CityToUserModel
import com.example.TeamFinder.repository.CityRepository.CityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class CityToUserRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired val cityRepository: CityRepository,
) : CityToUserRepository {
    override fun getCityByUserId(userId: Int): String =
        cityRepository.findCityById(
            jdbcTemplate.query(
                "select * from CityToUserTable where userId = :userId",
                mapOf("userId" to userId),
                ROW_MAPPER,
            ).first().cityId
        )


    override fun getUserIdByCityId(cityId: Int): List<CityToUserModel> =
        jdbcTemplate.query(
            "select * from CityToUserTable where cityId = :cityId",
            mapOf(
                "cityId" to cityId,
            ),
            ROW_MAPPER
        )

    override fun createByCityNameAndUserId(cityName: String, userId: Int) {
        val cityId = cityRepository.findCityByName(cityName)
        jdbcTemplate.update(
            "insert into CityToUserTable (cityId, userId) values (:cityId, :userId)",
            mapOf(
                "cityId" to cityId,
                "userId" to userId,
            )
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<CityToUserModel> { it, _ ->
            CityToUserModel(
                cityId = it.getInt("cityId"),
                userId = it.getInt("userId"),
            )
        }
    }
}