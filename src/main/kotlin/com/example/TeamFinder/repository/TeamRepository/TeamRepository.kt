package com.example.TeamFinder.repository.TeamRepository

import com.example.TeamFinder.model.Team.TeamModel

interface TeamRepository {

    fun setByUserIdAndTeamId(userId: Int, teamId: Int)

    fun getByTeamId(teamId: Int): List<TeamModel>

    fun getByUserId(userId: Int): List<TeamModel>

    fun removeFromTeamByUserIdAndTeamId(userId: Int, teamId: Int)
}