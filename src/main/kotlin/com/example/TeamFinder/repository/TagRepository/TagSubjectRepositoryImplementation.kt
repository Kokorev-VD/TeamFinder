package com.example.TeamFinder.repository.TagRepository

import com.example.TeamFinder.model.Tag.TagSubjectModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class TagSubjectRepositoryImplementation(
    @Autowired private val jdbcTemplate: NamedParameterJdbcTemplate,
) : TagSubjectRepository {

    override fun setTagSubject(id: Int, subject: String) {
        jdbcTemplate.update(
            "insert into TagSubjectTable values (:id, :subject)",
            mapOf(
                "id" to id,
                "subject" to subject,
            )
        )
    }

    override fun getTagSubjectById(id: Int): TagSubjectModel =
        jdbcTemplate.query(
            "select * from TagSubjectTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first()

    companion object {
        private val ROW_MAPPER = RowMapper<TagSubjectModel> { rs, _ ->
            TagSubjectModel(
                id = rs.getInt("id"),
                subject = rs.getString("subject"),
            )
        }
    }
}