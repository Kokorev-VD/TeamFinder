package com.example.TeamFinder.service.Recommendation

interface RecommendationService {

    fun calculateUserRankByPostId(userId: Int, postId: Int): Int

    fun calculatePostRankByUserId(userId: Int, postId: Int): Int

    fun recommendedPostIdByUserId(userId: Int): List<Int>

    fun recommendedUserIdByPostId(postId: Int): List<Int>

}