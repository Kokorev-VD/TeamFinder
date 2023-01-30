package com.example.TeamFinder.repository.JobToUserRepository

import com.example.TeamFinder.model.JobToUser.JobToUserModel
import com.example.TeamFinder.repository.JobRepository.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class JobToUserRepositoryImplementation(
    @Autowired val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired val jobRepository: JobRepository,
) : JobToUserRepository {
    override fun getJobByUserId(userId: Int): List<JobToUserModel> =
        jdbcTemplate.query(
            "select * from JobToUserTable where userId = :userId",
            mapOf(
                "userId" to userId,
            ),
            ROW_MAPPER
        )

    override fun getUserIdByJobId(jobId: Int): List<JobToUserModel> =
        jdbcTemplate.query(
            "select * from JobToUserTable where jobId = :jobId",
            mapOf(
                "jobId" to jobId,
            ),
            ROW_MAPPER
        )

    override fun createByJobNameAndUserId(jobName: String, userId: Int) {
        val jobId = jobRepository.findJobByName(jobName)
        jdbcTemplate.update(
            "insert into JobToUserTable (jobId, userId) values (:jobId, :userId)",
            mapOf(
                "jobId" to jobId,
                "userId" to userId,
            )
        )
    }

    override fun deleteByUserId(userId: Int) {
        jdbcTemplate.update(
            "delete from JobToUserTable where userId = :userId",
            mapOf(
                "userId" to userId,
            )
        )
    }

    override fun update(userId: Int, jobNameList: List<String>) {
        val job = getJobByUserId(userId)
        deleteByUserId(userId)
        for (i in job) {
            jobRepository.deleteById(i.jobId)
        }
        for (jobName in jobNameList) {
            createByJobNameAndUserId(jobName, userId)
        }
    }

    companion object {
        val ROW_MAPPER = RowMapper<JobToUserModel> { it, _ ->
            JobToUserModel(
                jobId = it.getInt("jobId"),
                userId = it.getInt("userId"),
            )
        }
    }
}