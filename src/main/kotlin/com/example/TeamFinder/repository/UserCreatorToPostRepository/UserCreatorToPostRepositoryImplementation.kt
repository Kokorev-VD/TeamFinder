package com.example.TeamFinder.repository.UserCreatorToPostRepository

import com.example.TeamFinder.model.UserCreatorToPost.UserCreatorToPostModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Component
@Repository
class UserCreatorToPostRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : UserCreatorToPostRepository {
    override fun setUserCreatorIdToPost(userId: Int, postId: Int) {
        jdbcTemplate.update(
            "insert into UserCreatorToPostTable (userId, postId) values (:userId, :postId)",
            mapOf(
                "userId" to userId,
                "postId" to postId,
            )
        )
    }

    override fun getUserCreatorToPostModelByUserId(userId: Int): List<UserCreatorToPostModel> =
        jdbcTemplate.query(
            "select * from UserCreatorToPostTable where userId = :userId",
            mapOf(
                "userId" to userId,
            ),
            ROW_MAPPER,
        )


    override fun getUserCreatorToPostModelByPostId(postId: Int): UserCreatorToPostModel =
        jdbcTemplate.query(
            "select * from UserCreatorToPostTable where postId = :postId",
            mapOf(
                "postId" to postId,
            ),
            ROW_MAPPER,
        ).first()

    override fun deleteByPostId(postId: Int) {
        jdbcTemplate.update(
            "delete from UserCreatorToPostTable where postId = :postId",
            mapOf(
                "postId" to postId
            )
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<UserCreatorToPostModel> { rs, _ ->
            UserCreatorToPostModel(
                userId = rs.getInt("userId"),
                postId = rs.getInt("postId"),
            )
        }
    }
}