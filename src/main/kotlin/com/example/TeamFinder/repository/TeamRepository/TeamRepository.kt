package com.example.TeamFinder.repository.TeamRepository

import com.example.TeamFinder.model.Team.TeamModel

interface TeamRepository {

    fun setByUserIdAndPostId(userId: Int, postId: Int)

    fun getByPostId(postId: Int): List<TeamModel>

    fun getByUserId(userId: Int): List<TeamModel>

}