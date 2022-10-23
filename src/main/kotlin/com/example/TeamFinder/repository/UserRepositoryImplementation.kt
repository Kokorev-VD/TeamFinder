package com.example.TeamFinder.repository

import com.example.TeamFinder.model.UserModel
import dto.User
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class UserRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : UserRepository {

    override fun getAll(): List<UserModel> =
        jdbcTemplate.query(
            "select * from userTable",
            ROW_MAPPER
        )

    override fun userParameterMapper(
        login: String,
        password: String,
        tg: String,
        description: String,
        role: String,
        imageId: Int
    ): Map<String, Any> {
        val lastIdUserModel = findLastId()
        return mapOf(
            "id" to lastIdUserModel!!.id + 1,
            "login" to login,
            "password" to password,
            "tg" to tg,
            "description" to description,
            "role" to role,
            "imageId" to imageId,
        )
    }

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


    override fun create(login: String, password: String, tg: String, description: String, role:String, imageId:Int): Int {
        val lastIdUserModel = findLastId()
        if(findByLogin(login) == null) {
            jdbcTemplate.update(
                "insert into userTable (id, login, password, tg, description, role, imageId)" +
                        " values (:id, :login, :password, :tg, :description, :role, :imageId)",
                MapSqlParameterSource(
                    userParameterMapper(login, password, tg, description, role, imageId)
                ),
            )
            return lastIdUserModel!!.id + 1
        }
        else{
            return -2
        }
    }

    override fun update(id: Int, user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    private companion object{
        val ROW_MAPPER = RowMapper<UserModel>{
            rs, _ ->
            UserModel(
                id = rs.getInt("id"),
                login = rs.getString("login"),
                password = rs.getString("password"),
                tg = rs.getString("tg"),
                description = rs.getString("description"),
                role = rs.getString("role"),
                imageId = rs.getInt("imageId"),
            )
        }
    }
}