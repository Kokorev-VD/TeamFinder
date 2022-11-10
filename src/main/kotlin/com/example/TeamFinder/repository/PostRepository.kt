package com.example.TeamFinder.repository

import com.example.TeamFinder.model.PostModel

interface PostRepository {

    fun findById(id: Int): PostModel?

    fun findByCreator(creator: Int): List<PostModel>

    fun findLastId(): PostModel?

    fun create(creator: Int, header: String, body: String): Int

    fun update(id: Int, newPost: PostModel): Int

    fun markUpdate(id: Int, markChange: Int, markType: String)

    fun deleteById(id: Int): Int

}