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
    override fun getByTitle(title: String): TagModel =
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

    override fun setByIdAndTitle(id: Int, subjectId: Int, title: String) {
        jdbcTemplate.update(
            "insert into TagTable values(:id, :subjectId, :title)",
            mapOf(
                "id" to id,
                "subjectId" to subjectId,
                "title" to title,
            )
        )
    }


    private companion object {
        val ROW_MAPPER = RowMapper<TagModel> { rs, _ ->
            TagModel(
                id = rs.getInt("id"),
                subjectId = rs.getInt("subjectId"),
                title = rs.getString("title"),
            )
        }
    }
}