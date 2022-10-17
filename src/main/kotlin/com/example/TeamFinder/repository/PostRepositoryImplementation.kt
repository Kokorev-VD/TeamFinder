package com.example.TeamFinder.repository

import com.example.TeamFinder.model.PostModel
import com.example.TeamFinder.model.UserModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PostRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : PostRepository {


    override fun findById(id: Int): PostModel? =
        jdbcTemplate.query(
            "select * from postTable where id = :id",
            mapOf(
                "id" to id,
            ),
            PostRepositoryImplementation.ROW_MAPPER
        ).firstOrNull()

    override fun findByCreator(creator: String): List<PostModel> =
        jdbcTemplate.query(
            "select * from postTable where creator = :creator",
            mapOf(
                "creator" to creator,
            ),
            PostRepositoryImplementation.ROW_MAPPER
        )


    override fun findLastId(): PostModel? =
        jdbcTemplate.query(
            "select * from postTable where id = (select max(id) from postTable)",
            PostRepositoryImplementation.ROW_MAPPER
        ).firstOrNull()

    override fun create(creator: String, header: String, body: String): Int {
        val lastIdPostModel = findLastId()?.id ?: 0
            jdbcTemplate.update(
                "insert into userTable (id, creator, header, body, pos_mark, neg_mark) " +
                        "values (id:, creator:, header:, body:, pos_mark:, neg_mark:)",
                MapSqlParameterSource(
                    mapOf(
                        "id" to lastIdPostModel+1,
                        "creator" to creator,
                        "header" to header,
                        "body" to body,
                        "pos_mark" to 0,
                        "neg_mark" to 0,
                    )
                ),
            )
            return lastIdPostModel+ 1
        }

    override fun update(id: Int, new_post: PostModel) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    private companion object{
        val ROW_MAPPER = RowMapper<PostModel>{
                rs, _ ->
            PostModel(
                id = rs.getInt("id"),
                creator = rs.getString("creator"),
                header = rs.getString("header"),
                body = rs.getString("body"),
                pos_mark = rs.getInt("pos_mark"),
                neg_mark = rs.getInt("neg_mark"),
            )
        }
    }
}