package com.example.TeamFinder.repository.JobToUserRepository

import com.example.TeamFinder.model.JobToUser.JobToUserModel

interface JobToUserRepository {

    fun getJobByUserId(userId: Int): List<JobToUserModel>

    fun getUserIdByJobId(jobId: Int): List<JobToUserModel>

    fun createByJobNameAndUserId(jobName: String, userId: Int)

    fun deleteByUserId(userId: Int)

    fun update(userId: Int, jobNameList: List<String>)
}