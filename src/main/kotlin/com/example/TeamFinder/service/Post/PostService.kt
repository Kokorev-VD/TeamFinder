package com.example.TeamFinder.service.Post

import com.example.TeamFinder.dto.Post.Post

interface PostService {

    fun getById(id: Int): Post

    fun create(newPost: Post)

    fun update(id: Int, newPost: Post)

    fun markUpdate(postId: Int, userId: Int, markType: Int)

    fun deleteById(id: Int)

}