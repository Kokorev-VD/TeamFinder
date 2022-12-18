package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.model.Post.PostExtensionModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class PostToPostRepositoryImplementation(
    val jdbcTemplate: NamedParameterJdbcTemplate,
) : PostToPostRepository {
    override fun setByBasedPostIdAndDerivedPostId(basedPostId: Int, derivedPostId: Int) {
        jdbcTemplate.update(
            "insert into postExtensionTable (basedPostId, derivedPostId) values (:basedPostId, :derivedPostId)",
            mapOf(
                "basedPostId" to basedPostId,
                "derivedPostId" to derivedPostId,
            )
        )
    }

    override fun getByBasedPostId(basedPostId: Int): List<PostExtensionModel> =
        jdbcTemplate.query(
            "select * from postExtensionTable where basedPostId = :basedPostId",
            mapOf(
                "basedPostId" to basedPostId,
            ),
            ROW_MAPPER
        )

    override fun getByDerivadPostId(derivedPostId: Int): List<PostExtensionModel> =
        jdbcTemplate.query(
            "select * from postExtensionTable where derivedPostId = :derivedPostId",
            mapOf(
                "basedPostId" to derivedPostId,
            ),
            ROW_MAPPER
        )

    companion object {
        val ROW_MAPPER = RowMapper<PostExtensionModel> { it, _ ->
            PostExtensionModel(
                basedPostId = it.getInt("basedPostId"),
                derivedPostId = it.getInt("derivedPostId"),
            )
        }
    }
}