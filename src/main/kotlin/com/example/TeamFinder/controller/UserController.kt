package com.example.TeamFinder.controller

import com.example.TeamFinder.dto.Mark.Mark
import com.example.TeamFinder.dto.Mark.MarkWithPost
import com.example.TeamFinder.dto.Post.Post
import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.*
import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel
import com.example.TeamFinder.service.Post.PostService
import com.example.TeamFinder.service.Recommendation.RecommendationService
import com.example.TeamFinder.service.Team.TeamService
import com.example.TeamFinder.service.User.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class Controller(
    private val userService: UserService,
    private val postService: PostService,
    private val teamService: TeamService,
    private val recommendationService: RecommendationService,
) {

    // Методы, которые относятся к User
    @GetMapping("/user/profile/{id}")
    fun getUserById(@PathVariable id: Int): User {
        return userService.getById(id)
    }

    @GetMapping("/user/achievement/{id}")
    fun getUserAchievementById(@PathVariable id: Int): UserAchievement =
        userService.getUserAchievement(id)

    @GetMapping("/user/tag/{id}")
    fun getUserTagById(@PathVariable id: Int): UserTag =
        userService.getUserTags(id)

    @GetMapping("/user/team/{id}")
    fun getUserTeamById(@PathVariable id: Int): UserTeam =
        userService.getUserTeam(id)

    @GetMapping("/user/mark/{id}")
    fun getUserMark(@PathVariable id: Int): List<MarkWithPost> =
        userService.getUserMarks(id)

    @GetMapping("/user/post/{id}")
    fun getUserPost(@PathVariable id: Int): UserPost =
        userService.getUserPost(id)

    @GetMapping("/achievement/types")
    fun getAchievementTypes(): List<String> =
        userService.getAchievementTypes()

    @GetMapping("/user/city/{id}")
    fun getCityByUserId(@PathVariable id: Int): UserCity =
        userService.getUserCity(id)

    @GetMapping("/user/job/{id}")
    fun getJobListByUserId(@PathVariable id: Int): UserJob =
        userService.getUserJob(id)

    @PutMapping("/user/update/job")
    fun setJobToUser(@RequestBody job: UserJob) {
        userService.setUserJob(job.userId, job.userJob)
    }

    @PutMapping("/user/update/city")
    fun setCityByUserCity(@RequestBody userCity: UserCity) {
        userService.setUserCity(userCity.userId, userCity.cityName)
    }

    @PutMapping("/user/update/model")
    fun updateUserById(@RequestBody userParams: UserModel) {
        userService.updateUserInfo(userParams)
    }

    @PutMapping("/user/update/achievement")
    fun updateUserAchievements(@RequestBody userAchievement: UserAchievement) {
        userService.setUserAchievement(userAchievement)
    }

    @PutMapping("/user/update/tag")
    fun updateUserTags(@RequestBody userTag: UserTag) {
        userService.updateUserTag(userTag.id, userTag.tag)
    }

    @PostMapping("/auth/log")
    fun authorizeNewUser(@RequestBody user: UserLoginParamsModel): Response =
        userService.authorisation(user)


    @PostMapping("/auth/reg")
    fun registration(@RequestBody user: UserLoginParamsModel): Response =
        userService.registration(user.login, user.pass)

    //Методы, которые относятся к Post

    @GetMapping("/post/{id}")
    fun getPostById(@PathVariable id: Int) =
        postService.getById(id)

    @PutMapping("/post/updateMark")
    fun updateMArk(@RequestBody mark: Mark) {
        postService.markUpdate(mark.postId, mark.userId, mark.markType)
    }

    @PostMapping("/post/new")
    fun createNewPost(@RequestBody newPost: Post) =
        postService.create(newPost)

    @PutMapping("/post/update/{postId}")
    fun updatePostById(@PathVariable postId: Int, @RequestBody newPost: Post) =
        postService.update(postId, newPost)


    @DeleteMapping("/post/delete")
    fun deletePostById(@RequestBody id: Int) =
        postService.deleteById(id)

    // Методы, которые относятся к Team

    @GetMapping("/team/{teamId}")
    fun getTeamByTeamId(@PathVariable teamId: Int): List<User> =
        teamService.readTeamByPostId(teamId)

    @PostMapping("/team/{teamId}/add/{userId}")
    fun addUserToTeam(@PathVariable teamId: Int, @PathVariable userId: Int) {
        teamService.addUserToTeam(userId, teamId)
    }

    @PutMapping("/team/{teamId}/remove/{userId}")
    fun removeUserFromTeam(@PathVariable teamId: Int, @PathVariable userId: Int) {
        teamService.removeUserFromTeam(userId, teamId)
    }

    // Рекомендательная система

    @GetMapping("recommend/post/{postId}")
    fun recommendUserByPostId(@PathVariable postId: Int): List<Int> =
        recommendationService.recommendedUserIdByPostId(postId)

    @GetMapping("recommend/user/{userId}")
    fun recommendPostByUserId(@PathVariable userId: Int): List<Int> =
        recommendationService.recommendedPostIdByUserId(userId)

}