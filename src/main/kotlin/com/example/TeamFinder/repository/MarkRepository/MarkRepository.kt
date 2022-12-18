package com.example.TeamFinder.repository.MarkRepository

interface MarkRepository {

    fun getPosMarksByPostId(postId: Int): Int

    fun getNegMarksByPostId(postId: Int): Int

    fun setByPostIdAndUserId(postId: Int, userId: Int, markType: Int)

}