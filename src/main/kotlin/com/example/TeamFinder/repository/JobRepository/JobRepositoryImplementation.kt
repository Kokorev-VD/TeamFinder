package com.example.TeamFinder.repository.JobRepository

import com.example.TeamFinder.model.Job.JobModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class JobRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
) : JobRepository {

    override fun findLastId(): Int =
        jdbcTemplate.query(
            "select max(id) from JobTable",
            ROW_MAPPER
        ).first().id

    override fun createNewJob(name: String) {
        val id = findLastId()
        jdbcTemplate.update(
            "insert into JobTable (id, name) values (:id, :name)",
            mapOf(
                "id" to id,
                "name" to name,
            )
        )
    }

    override fun findJobById(id: Int): String =
        jdbcTemplate.query(
            "select * from JobTable where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).first().name


    override fun findJobByName(name: String): Int {
        return if (jdbcTemplate.query(
                "select * from JobTable where name = :name", mapOf("name" to name),
                ROW_MAPPER
            ).firstOrNull() == null
        ) {
            createNewJob(name)
            findLastId() - 1
        } else {
            jdbcTemplate.query(
                "select * from JobTable where name = :name", mapOf("name" to name),
                ROW_MAPPER
            ).first().id
        }
    }

    companion object {
        val ROW_MAPPER = RowMapper<JobModel> { it, _ ->
            JobModel(
                id = it.getInt("id"),
                name = it.getString("name"),
            )
        }
    }
}