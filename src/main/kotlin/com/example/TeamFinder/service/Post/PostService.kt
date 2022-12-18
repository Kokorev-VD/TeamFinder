package com.example.TeamFinder.service.Post

import com.example.TeamFinder.dto.Post.Post

interface PostService {

    fun getById(id: Int): Post
//
//    fun getByCreator(creator: Int): List<Post>
//
//    fun findLastId(): Post
//
//    fun create(newPost: Post): Int
//
//    fun update(id: Int, newPost: Post): Int
//
//    fun markUpdate(id: Int, markChange: Int, markType: String)
//
//    fun deleteById(id: Int): Int

}