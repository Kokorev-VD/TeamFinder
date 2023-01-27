package com.example.TeamFinder.service.Team

import com.example.TeamFinder.dto.User.User
import com.example.TeamFinder.repository.TeamRepository.TeamRepository
import com.example.TeamFinder.repository.UserCreatorToPostRepository.UserCreatorToPostRepository
import com.example.TeamFinder.repository.UserRepository.UserLoginParamsRepository
import com.example.TeamFinder.service.User.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
class TeamServiceImplementation(
    @Autowired private val teamRepository: TeamRepository,
    @Autowired private val userLoginParamsRepository: UserLoginParamsRepository,
    @Autowired private val userCreatorToPostRepository: UserCreatorToPostRepository,
    @Autowired private val userService: UserService,
) : TeamService {

    override fun createTeam(postId: Int) {
        teamRepository.setByUserIdAndTeamId(
            userCreatorToPostRepository.getUserCreatorToPostModelByPostId(postId).userId, postId
        )
    }

    override fun addUserToTeam(userId: Int, teamId: Int) {
        teamRepository.setByUserIdAndTeamId(userId, teamId)
    }

    override fun removeUserFromTeam(userId: Int, teamId: Int) {
        teamRepository.removeFromTeamByUserIdAndTeamId(userId, teamId)
    }

    override fun readTeamByPostId(postId: Int): List<User> {
        val list = teamRepository.getByTeamId(postId)
        val res = mutableListOf<User>()
        for (i in list) {
            res.add(userService.getById(i.userId))
        }
        return res
    }
}