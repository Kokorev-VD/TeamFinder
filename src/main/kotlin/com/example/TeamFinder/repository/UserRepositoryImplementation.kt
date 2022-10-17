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

    override fun getAll(): List<UserModel>  =
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


    override fun create(login: String, password: String): Int {
        val lastIdUserModel = findLastId()
        if(findByLogin(login) == null) {
            jdbcTemplate.update(
                "insert into userTable (id, login, password) values (:id, :login, :password)",
                MapSqlParameterSource(
                    mapOf(
                        "id" to lastIdUserModel!!.id+1,
                        "login" to login,
                        "password" to password,
                    )
                ),
            )
            return lastIdUserModel.id + 1
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
            )
        }
    }
}