package com.example.TeamFinder.repository.MarkRepository

import com.example.TeamFinder.dto.Mark.MarkWithStringPost

interface MarkRepository {

    fun getPosMarksByPostId(postId: Int): Int

    fun getNegMarksByPostId(postId: Int): Int

    fun setByPostIdAndUserId(postId: Int, userId: Int, markType: Int)

    fun getMarkWithStringPostByUserId(userId: Int): List<MarkWithStringPost>

    fun deleteByPostIdAndUserId(postId: Int, userId: Int)

    fun update(postId: Int, userId: Int, markType: Int)
}