package com.example.TeamFinder.repository

import com.example.TeamFinder.model.PostModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class PostRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : PostRepository {


    override fun findById(id: Int): PostModel? =
        jdbcTemplate.query(
            "select * from postTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).firstOrNull()

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

    override fun create(creator: Int, header: String, body: String): Int {
        val lastIdPostModel = findLastId()?.id ?: -1
        jdbcTemplate.update(
            "insert into postTable (id, creator, header, body, pos_mark, neg_mark) " +
                    "values (:id, :creator, :header, :body, :pos_mark, :neg_mark)",
            MapSqlParameterSource(
                mapOf(
                    "id" to lastIdPostModel + 1,
                    "creator" to creator,
                    "header" to header,
                    "body" to body,
                    "pos_mark" to 0,
                    "neg_mark" to 0,
                )
            ),
        )
        return lastIdPostModel + 1
    }

    override fun update(id: Int, newPost: PostModel): Int {
        if (findById(id) == null) {
            return 200
        }
        jdbcTemplate.update(
            "update postTable set creator = :creator, header = :header, body = :body, pos_mark = :pos_mark," +
                    "neg_mark = :neg_mark where id = :id",
            MapSqlParameterSource(
                mapOf(
                    "id" to id,
                    "creator" to newPost.creator,
                    "header" to newPost.header,
                    "body" to newPost.body,
                    "pos_mark" to newPost.pos_mark,
                    "neg_mark" to newPost.neg_mark,
                )
            ),
        )
        return 100
    }


    override fun markUpdate(id: Int, markChange: Int, markType: String) {
        jdbcTemplate.update(
            "update postTable set ${markType}_mark = :newMark where id = :id",
            mapOf(
                "newMark" to if (markType == "pos") findById(id)!!.pos_mark + markChange else findById(id)!!.pos_mark + markChange,
                "id" to id,
            )
        )
    }


    override fun deleteById(id: Int): Int {
        if (findById(id) == null) {
            return 200
        }
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
                creator = rs.getInt("creator"),
                header = rs.getString("header"),
                body = rs.getString("body"),
                pos_mark = rs.getInt("pos_mark"),
                neg_mark = rs.getInt("neg_mark"),
            )
        }
    }
}