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

    override fun setByUserIdAndTeamId(userId: Int, teamId: Int) {
        jdbcTemplate.update(
            "insert into TeamTable (userId, teamId) values (:userId, :teamId)",
            mapOf(
                "userId" to userId,
                "teamId" to teamId,
            )
        )
    }

    override fun getByTeamId(teamId: Int): List<TeamModel> =
        jdbcTemplate.query(
            "select * from TeamTable where teamId = :teamId",
            mapOf(
                "teamId" to teamId
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

    override fun removeFromTeamByUserIdAndTeamId(userId: Int, teamId: Int) {
        jdbcTemplate.update(
            "delete from TeamTable where userId = :userId and teamId = :teamId",
            mapOf(
                "userId" to userId,
                "teamId" to teamId,
            )
        )
    }


    companion object {
        val ROW_MAPPER = RowMapper<TeamModel> { it, _ ->
            TeamModel(
                teamId = it.getInt("teamId"),
                userId = it.getInt("userId"),
            )
        }
    }
}