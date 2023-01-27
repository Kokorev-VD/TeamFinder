package com.example.TeamFinder.service.Team

import com.example.TeamFinder.dto.User.User

interface TeamService {

    fun createTeam(teamId: Int)

    fun addUserToTeam(userId: Int, teamId: Int)

    fun removeUserFromTeam(userId: Int, teamId: Int)

    fun readTeamByPostId(postId: Int): List<User>

}