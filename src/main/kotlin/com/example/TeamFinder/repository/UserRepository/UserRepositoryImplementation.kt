package com.example.TeamFinder.repository.UserRepository

import com.example.TeamFinder.model.User.UserModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class UserRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val userLoginParamsRepository: UserLoginParamsRepository,
) : UserRepository {


    override fun findById(id: Int): UserModel =
        jdbcTemplate.query(
            "select * from userTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first()

    override fun create(id: Int, tg: String, description: String, imageId: Int) {
        jdbcTemplate.update(
            "insert into userTable (id, tg, description, imageId)" +
                    " values (:id, :tg, :description, :imageId)",
            MapSqlParameterSource(
                mapOf(
                    "id" to id,
                    "tg" to tg,
                    "description" to description,
                    "imageId" to imageId,
                )
            ),
        )
    }


    override fun update(id: Int, tg: String, description: String, imageId: Int) {
        delete(id)
        create(id, tg, description, imageId)
    }

    override fun delete(id: Int) {
        jdbcTemplate.update(
            "delete from userTable where id = :id",
            mapOf(
                "id" to id,
            )
        )
    }


    private companion object {
        val ROW_MAPPER = RowMapper<UserModel> { rs, _ ->
            UserModel(
                id = rs.getInt("id"),
                tg = rs.getString("tg"),
                description = rs.getString("description"),
                imageId = rs.getInt("imageId"),
            )
        }
    }
}