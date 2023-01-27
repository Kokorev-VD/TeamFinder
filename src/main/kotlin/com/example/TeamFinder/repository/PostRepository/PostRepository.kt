package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.model.Post.PostModel

interface PostRepository {

    fun getAllPost(): List<PostModel>

    fun findById(id: Int): PostModel

    fun findByCreator(creator: Int): List<PostModel>

    fun findLastId(): PostModel?

    fun create(title: String, body: String): Int

    fun update(id: Int, newPost: PostModel): Int

    fun deleteById(id: Int): Int

}