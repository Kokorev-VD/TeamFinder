package com.example.TeamFinder.repository

import com.example.TeamFinder.model.PostModel

interface PostRepository {

    fun findById(id: Int): PostModel?

    fun findByCreator(creator: String): List<PostModel>

    fun findLastId(): PostModel?

    fun create(creator: String, header: String, body: String): Int

    fun update(id: Int, new_post:PostModel)

    fun deleteById(id: Int)

}