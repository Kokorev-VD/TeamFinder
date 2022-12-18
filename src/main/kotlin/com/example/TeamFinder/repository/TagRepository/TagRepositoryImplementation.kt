package com.example.TeamFinder.repository.TagRepository

import com.example.TeamFinder.model.Tag.TagModel
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Component
@Repository
class TagRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : TagRepository {
    override fun getByTitile(title: String): TagModel =
        jdbcTemplate.query(
            "select * from tagTable where title = :title",
            mapOf(
                "title" to title
            ),
            ROW_MAPPER
        ).first()

    override fun getById(id: Int): TagModel =
        jdbcTemplate.query(
            "select * from tagTable where id = :id",
            mapOf(
                "id" to id
            ),
            ROW_MAPPER
        ).first()


    private companion object {
        val ROW_MAPPER = RowMapper<TagModel> { rs, _ ->
            TagModel(
                id = rs.getInt("id"),
                title = rs.getString("title"),
            )
        }
    }
}