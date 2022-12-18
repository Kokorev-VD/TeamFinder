package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.model.Post.PostModel

interface PostRepository {

    fun findById(id: Int): PostModel

    fun findByCreator(creator: Int): List<PostModel>

    fun findLastId(): PostModel?

    fun create(creator: Int, header: String, body: String): Int

    fun update(id: Int, newPost: PostModel): Int

    fun deleteById(id: Int): Int

}