package com.example.TeamFinder.repository.UserRepository

import com.example.TeamFinder.model.User.UserLoginParamsModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class UserLoginParamsRepositoryImplementation(
    val jdbcTemplate: NamedParameterJdbcTemplate,
) : UserLoginParamsRepository {

    override fun getById(id: Int): UserLoginParamsModel =
        jdbcTemplate.query(
            "select * from UserLoginParamsTable where id = :id",
            mapOf(
                "id" to id
            ),
            ROW_MAPPER
        ).first()

    override fun getLastId(): Int =
        jdbcTemplate.query(
            "select * from userLoginParamsTable where id = (select max(id) from userLoginParamsTable)",
            ROW_MAPPER
        ).first().id

    override fun getAllUserLoginParams(): List<UserLoginParamsModel> =
        jdbcTemplate.query(
            "select * from userLoginParamsTable",
            ROW_MAPPER,
        )


    override fun getByLogin(login: String): UserLoginParamsModel? =
        jdbcTemplate.query(
            "select * from UserLoginParamsTable where login = :login",
            mapOf(
                "login" to login
            ),
            ROW_MAPPER
        ).firstOrNull()

    override fun create(login: String, pass: String): Int {
        val id = getLastId() + 1
        jdbcTemplate.update(
            "insert into UserLoginParamsTable (id, login, pass) values (:id, :login, :pass)",
            mapOf(
                "id" to id,
                "login" to login,
                "pass" to pass,
            )
        )
        return id
    }

    private companion object {
        val ROW_MAPPER = RowMapper<UserLoginParamsModel> { rs, _ ->
            UserLoginParamsModel(
                id = rs.getInt("id"),
                login = rs.getString("login"),
                pass = rs.getString("pass"),
            )
        }
    }
}