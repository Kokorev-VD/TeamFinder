package com.example.TeamFinder.repository.JobRepository

interface JobRepository {

    fun findLastId(): Int

    fun createNewJob(name: String)

    fun findJobById(id: Int): String

    fun findJobByName(name: String): Int

    fun deleteById(id: Int)
}