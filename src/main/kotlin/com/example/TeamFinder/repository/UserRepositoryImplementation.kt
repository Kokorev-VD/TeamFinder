package com.example.TeamFinder.repository

import com.example.TeamFinder.model.UserModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class UserRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : UserRepository {

    override fun getAll(): List<UserModel> =
        jdbcTemplate.query(
            "select * from userTable",
            ROW_MAPPER
        )

    override fun findById(id: Int): UserModel? =
        jdbcTemplate.query(
            "select * from userTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).firstOrNull()

    override fun findByLogin(login: String): UserModel? =
        jdbcTemplate.query(
            "select * from userTable where login = :login",
            mapOf(
                "login" to login,
            ),
            ROW_MAPPER
        ).firstOrNull()

    override fun findLastId(): UserModel? =
        jdbcTemplate.query(
            "select * from userTable where id = (select max(id) from userTable)",
            ROW_MAPPER
    ).firstOrNull()

//    SPRING REPOSITORIES

    override fun create(
        login: String,
        password: String,
        tg: String,
        description: String,
        role: String,
        imageId: Int,
        access: Boolean,
        tags: String,
    ): Int {
        val lastIdUserModel = findLastId()
        if (findByLogin(login) == null) {
            jdbcTemplate.update(
                "insert into userTable (id, login, password, tg, description, role, imageId, access, tags)" +
                        " values (:id, :login, :password, :tg, :description, :role, :imageId, :access, :tags)",
                MapSqlParameterSource(
                    mapOf(
                        "id" to lastIdUserModel!!.id + 1,
                        "login" to login,
                        "password" to password,
                        "tg" to tg,
                        "description" to description,
                        "role" to role,
                        "imageId" to imageId,
                        "access" to access,
                        "tags" to tags,
                    )
                ),
            )
            return lastIdUserModel.id + 1
        }
        return 200
    }

    override fun update(id: Int, login: String, tg: String, description: String, imageId: Int, tags: String) {
        jdbcTemplate.update(
            "update userTable set login = :login, tg = :tg, description = :description, imageId = :imageId, tags = :tags where id = :id",
            mapOf(
                "login" to login,
                "tg" to tg,
                "description" to description,
                "imageId" to imageId,
                "tags" to tags,
                "id" to id,
            )
        )
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    private companion object {
        val ROW_MAPPER = RowMapper<UserModel> { rs, _ ->
            UserModel(
                id = rs.getInt("id"),
                login = rs.getString("login"),
                password = rs.getString("password"),
                tg = rs.getString("tg"),
                description = rs.getString("description"),
                role = rs.getString("role"),
                imageId = rs.getInt("imageId"),
                access = rs.getBoolean("access"),
                tags = rs.getString("tags")
            )
        }
    }
}