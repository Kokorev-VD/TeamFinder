package com.example.TeamFinder.service

import com.example.TeamFinder.dto.Post

interface PostService {

    fun getById(id: Int): Post

    fun getByCreator(creator: String): List<Post>

    fun findLastId(): Post

    fun create(new_post: Post): Int

    fun update(id: Int, new_post: Post)

    fun deleteById(id: Int)

}