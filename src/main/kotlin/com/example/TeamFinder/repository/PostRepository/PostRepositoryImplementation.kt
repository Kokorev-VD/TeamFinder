package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.model.Post.PostModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class PostRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : PostRepository {
    override fun getAllPost(): List<PostModel> =
        jdbcTemplate.query(
            "select * from postTable",
            ROW_MAPPER,
        )


    override fun findById(id: Int): PostModel =
        jdbcTemplate.query(
            "select * from postTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first()

    override fun findByCreator(creator: Int): List<PostModel> {
        return jdbcTemplate.query(
            "select * from postTable where creator = :creator",
            mapOf(
                "creator" to creator,
            ),
            ROW_MAPPER
        )
    }


    override fun findLastId(): PostModel? =
        jdbcTemplate.query(
            "select * from postTable where id = (select max(id) from postTable)",
            ROW_MAPPER
        ).firstOrNull()

    override fun create(title: String, body: String): Int {
        val lastIdPostModel = findLastId()?.id ?: -1
        jdbcTemplate.update(
            "insert into postTable (id, title, body) " +
                    "values (:id ,:title, :body)",
            MapSqlParameterSource(
                mapOf(
                    "id" to lastIdPostModel + 1,
                    "title" to title,
                    "body" to body,
                )
            ),
        )
        return lastIdPostModel + 1
    }

    override fun update(id: Int, newPost: PostModel): Int {
        jdbcTemplate.update(
            "update postTable set title = :title, body = :body," +
                    " where id = :id",
            MapSqlParameterSource(
                mapOf(
                    "id" to id,
                    "title" to newPost.title,
                    "body" to newPost.body,
                )
            ),
        )
        return 100
    }



    override fun deleteById(id: Int): Int {
        jdbcTemplate.update(
            "delete from postTable where id = :id",
            mapOf(
                "id" to id,
            )
        )
        return 100
    }

    private companion object {
        val ROW_MAPPER = RowMapper<PostModel> { rs, _ ->
            PostModel(
                id = rs.getInt("id"),
                title = rs.getString("title"),
                body = rs.getString("body"),
            )
        }
    }
}