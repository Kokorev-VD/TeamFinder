package com.example.TeamFinder.repository.MarkRepository

import com.example.TeamFinder.dto.Mark.MarkWithPost

interface MarkRepository {

    fun getPosMarksByPostId(postId: Int): Int

    fun getNegMarksByPostId(postId: Int): Int

    fun setByPostIdAndUserId(postId: Int, userId: Int, markType: Int)

    fun getMarkWithStringPostByUserId(userId: Int): List<MarkWithPost>

    fun deleteByPostIdAndUserId(postId: Int, userId: Int)

    fun deleteByPostId(postId: Int)

    fun update(postId: Int, userId: Int, markType: Int)
}