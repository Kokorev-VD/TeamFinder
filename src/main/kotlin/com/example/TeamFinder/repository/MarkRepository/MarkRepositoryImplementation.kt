package com.example.TeamFinder.repository.MarkRepository

import com.example.TeamFinder.model.Mark.MarkModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class MarkRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : MarkRepository {
    override fun getPosMarksByPostId(postId: Int): Int =
        jdbcTemplate.query(
            "select * from MarkTable where markType = 1 and postId = :postId",
            mapOf(
                "postId" to postId
            ),
            ROW_MAPPER
        ).sumOf { it.markType }

    override fun getNegMarksByPostId(postId: Int): Int =
        jdbcTemplate.query(
            "select * from MarkTable where markType = -1 and postId = :postId",
            mapOf(
                "postId" to postId
            ),
            ROW_MAPPER
        ).sumOf { it.markType } * -1

    override fun setByPostIdAndUserId(postId: Int, userId: Int, markType: Int) {
        jdbcTemplate.update(
            "insert into MarkTable (postId, userId, markType) values (:postId, :userId, :markType)",
            mapOf(
                "postId" to postId,
                "userId" to userId,
                "markType" to markType,
            )
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<MarkModel> { it, _ ->
            MarkModel(
                postId = it.getInt("postId"),
                userId = it.getInt("userId"),
                markType = it.getInt("markType")
            )
        }
    }
}