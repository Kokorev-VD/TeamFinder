package com.example.TeamFinder.service.Team

import com.example.TeamFinder.dto.User.UserProfile
import com.example.TeamFinder.repository.TeamRepository.TeamRepository
import com.example.TeamFinder.repository.UserRepository.UserLoginParamsRepository
import com.example.TeamFinder.service.Post.PostService
import com.example.TeamFinder.service.User.UserService
import org.springframework.beans.factory.annotation.Autowired

class TeamServiceImplementation(
    @Autowired private val teamRepository: TeamRepository,
    @Autowired private val userLoginParamsRepository: UserLoginParamsRepository,
    @Autowired private val postService: PostService,
    @Autowired private val userService: UserService,
) : TeamService {

    override fun createTeam(teamId: Int) {
        teamRepository.setByUserIdAndTeamId(
            userLoginParamsRepository.getByLogin(postService.getById(teamId).creatorLogin)!!.id, teamId
        )
    }

    override fun addUserToTeam(userId: Int, teamId: Int) {
        teamRepository.setByUserIdAndTeamId(userId, teamId)
    }

    override fun removeUserFromTeam(userId: Int, teamId: Int) {
        teamRepository.removeFromTeamByUserIdAndTeamId(userId, teamId)
    }

    override fun readTeamByPostId(postId: Int): List<UserProfile> {
        val list = teamRepository.getByTeamId(postId)
        val res = mutableListOf<UserProfile>()
        for (i in list) {
            res.add(userService.getById(i.userId))
        }
        return res
    }

    override fun readTeamByUserId(userId: Int): List<UserProfile> {
        val list = teamRepository.getByUserId(userId)
        val res = mutableListOf<UserProfile>()
        for (i in list) {
            res.add(userService.getById(i.userId))
        }
        return res
    }

}