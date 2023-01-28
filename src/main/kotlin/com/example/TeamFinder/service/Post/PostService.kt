package com.example.TeamFinder.service.Post

import com.example.TeamFinder.dto.Post.*

interface PostService {

    fun getById(id: Int): MainInfoPost

    fun getPostTeamById(id: Int): PostTeam

    fun getPostMarkById(id: Int): PostMark

    fun getPostTagById(id: Int): PostTag

    fun getRelatedPost(id: Int): RelatedPost

    fun create(newPost: Post)

    fun updateMainInfoPost(newPost: MainInfoPost)

    fun updatePostTag(newPost: PostTag)

    fun updateRelatedPost(relatedPost: RelatedPost)

    fun markUpdate(postId: Int, userId: Int, markType: Int)

    fun deleteById(id: Int)

}