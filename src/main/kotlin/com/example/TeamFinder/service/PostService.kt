package com.example.TeamFinder.service

import com.example.TeamFinder.dto.Post

interface PostService {

    fun getById(id: Int): Post

    fun getByCreator(creator: Int): List<Post>

    fun findLastId(): Post

    fun create(newPost: Post): Int

    fun update(id: Int, newPost: Post)

    fun markUpdate(id: Int, markChange: Int, markType: String)

    fun deleteById(id: Int)

}