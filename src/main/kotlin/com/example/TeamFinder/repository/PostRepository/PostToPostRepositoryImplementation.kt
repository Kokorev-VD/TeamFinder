package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.dto.Post.MainInfoPost
import com.example.TeamFinder.model.Post.PostExtensionModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class PostToPostRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired val postRepository: PostRepository,
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

    override fun getByDerivedPostId(derivedPostId: Int): List<PostExtensionModel> =
        jdbcTemplate.query(
            "select * from postExtensionTable where derivedPostId = :derivedPostId",
            mapOf(
                "derivedPostId" to derivedPostId,
            ),
            ROW_MAPPER
        )

    override fun getBasedListMainInfoPost(id: Int): List<MainInfoPost> {
        val list = getByDerivedPostId(id)
        val res = mutableListOf<MainInfoPost>()
        for (i in list) {
            res.add(MainInfoPost(i.basedPostId, postRepository.findById(i.basedPostId).title))
        }
        return res.toList()
    }

    override fun getDerivedListMainInfoPost(id: Int): List<MainInfoPost> {
        val list = getByBasedPostId(id)
        val res = mutableListOf<MainInfoPost>()
        for (i in list) {
            res.add(MainInfoPost(i.derivedPostId, postRepository.findById(i.derivedPostId).title))
        }
        return res.toList()
    }

    companion object {
        val ROW_MAPPER = RowMapper<PostExtensionModel> { it, _ ->
            PostExtensionModel(
                basedPostId = it.getInt("basedPostId"),
                derivedPostId = it.getInt("derivedPostId"),
            )
        }
    }
}