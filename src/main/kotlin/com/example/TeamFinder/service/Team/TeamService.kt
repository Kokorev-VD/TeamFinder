package com.example.TeamFinder.service.Team

import com.example.TeamFinder.dto.User.UserProfile
import com.example.TeamFinder.model.Team.TeamModel

interface TeamService {

    fun createTeam(teamId: Int)

    fun addUserToTeam(userId: Int, teamId: Int)

    fun removeUserFromTeam(userId: Int, teamId: Int)

    fun readTeamByPostId(postId: Int): List<UserProfile>

    fun readTeamByUserId(userId: Int): List<TeamModel>

}