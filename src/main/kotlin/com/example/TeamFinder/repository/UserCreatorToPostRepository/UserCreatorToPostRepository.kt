package com.example.TeamFinder.repository.UserCreatorToPostRepository

import com.example.TeamFinder.model.UserCreatorToPost.UserCreatorToPostModel

interface UserCreatorToPostRepository {

    fun setUserCreatorIdToPost(userId: Int, postId: Int)

    fun getUserCreatorToPostModelByUserId(userId: Int): List<UserCreatorToPostModel>

    fun getUserCreatorToPostModelByPostId(postId: Int): UserCreatorToPostModel

    fun deleteByPostId(postId: Int)
}