package com.example.TeamFinder.repository.TeamRepository

import com.example.TeamFinder.model.Team.TeamModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class TeamRepositoryImplementation(
    val jdbcTemplate: NamedParameterJdbcTemplate,
) : TeamRepository {

    override fun setByUserIdAndPostId(userId: Int, postId: Int) {
        jdbcTemplate.update(
            "insert into TeamTable (userId, postId) values (:userId, :postId)",
            mapOf(
                "userId" to userId,
                "postId" to postId,
            )
        )
    }

    override fun getByPostId(postId: Int): List<TeamModel> =
        jdbcTemplate.query(
            "select * from TeamTable where postId = :postId",
            mapOf(
                "postId" to postId
            ),
            ROW_MAPPER
        )

    override fun getByUserId(userId: Int): List<TeamModel> =
        jdbcTemplate.query(
            "select * from TeamTable where userId = :userId",
            mapOf(
                "userId" to userId
            ),
            ROW_MAPPER
        )


    companion object {
        val ROW_MAPPER = RowMapper<TeamModel> { it, _ ->
            TeamModel(
                teamId = it.getInt("teamId"),
                userId = it.getInt("userId"),
            )
        }
    }
}