package com.example.TeamFinder.repository.TagToPostRepository

import com.example.TeamFinder.model.TagToPost.TagToPostModel
import com.example.TeamFinder.repository.TagRepository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class TagToPostRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired private val tagRepository: TagRepository,
) : TagToPostRepository {
    override fun getTagsByPostId(postId: Int): List<TagToPostModel> =
        jdbcTemplate.query(
            "select * from TagToPostTable where postId = :postId",
            mapOf(
                "postId" to postId,
            ),
            ROW_MAPPER
        )

    override fun setTagByPostIdAndTagTitle(postId: Int, tagTitle: String) {
        val tagId = tagRepository.getByTitile(tagTitle).id
        jdbcTemplate.update(
            "insert into TagToPostTable (tagId, postId) values (:tagId, :postId)",
            mapOf(
                "tagId" to tagId,
                "postId" to postId,
            )
        )
    }

    override fun deleteByPostIdAndTagTitle(postId: Int, tagTitle: String) {
        val tagId = tagRepository.getByTitile(tagTitle).id
        jdbcTemplate.update(
            "delete from TagToPostTable where postId = :postId and tagId = :tagId",
            mapOf(
                "tagId" to tagId,
                "postId" to postId,
            )
        )
    }

    companion object {
        val ROW_MAPPER = RowMapper<TagToPostModel> { rs, _ ->
            TagToPostModel(
                tagId = rs.getInt("tagId"),
                postId = rs.getInt("postId"),
            )
        }
    }
}