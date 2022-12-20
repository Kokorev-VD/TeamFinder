package com.example.TeamFinder.repository.MarkRepository

import com.example.TeamFinder.dto.Mark.MarkWithStringPost
import com.example.TeamFinder.model.Mark.MarkModel
import com.example.TeamFinder.repository.PostRepository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class MarkRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired val postRepository: PostRepository,
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

    override fun getMarkWithStringPostByUserId(userId: Int): List<MarkWithStringPost> {
        val marksList = jdbcTemplate.query(
            "select * from MarkTable where userId = :userId",
            mapOf(
                "userId" to userId
            ),
            ROW_MAPPER
        )
        val resList = mutableListOf<MarkWithStringPost>()
        for (i in marksList) {
            resList.add(MarkWithStringPost(i.postId, postRepository.findById(i.postId).title, i.markType))
        }
        return resList
    }

    override fun deleteByPostIdAndUserId(postId: Int, userId: Int) {
        jdbcTemplate.update(
            "delete from MarkTable where postId = :postId and userId = :userId",
            mapOf(
                "postId" to postId,
                "userId" to userId,
            )
        )
    }

    override fun update(postId: Int, userId: Int, markType: Int) {
        deleteByPostIdAndUserId(postId, userId)
        setByPostIdAndUserId(postId, userId, markType)
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