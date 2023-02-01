package com.example.TeamFinder.controller

import com.example.TeamFinder.dto.Mark.Mark
import com.example.TeamFinder.dto.Post.*
import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.*
import com.example.TeamFinder.service.Post.PostService
import com.example.TeamFinder.service.Recommendation.RecommendationService
import com.example.TeamFinder.service.Tag.TagService
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
    private val tagService: TagService,
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
    fun getUserMark(@PathVariable id: Int): UserMark =
        userService.getUserMark(id)

    @GetMapping("/user/post/{id}")
    fun getUserPost(@PathVariable id: Int): UserPost =
        userService.getUserPost(id)

    @GetMapping("/achievement/types")
    fun getAchievementTypes(): List<String> =
        userService.getAchievementTypes()

    @GetMapping("/user/city/{id}")
    fun getUserCityById(@PathVariable id: Int): UserCity =
        userService.getUserCity(id)

    @GetMapping("/user/job/{id}")
    fun getUserJobById(@PathVariable id: Int): UserJob =
        userService.getUserJob(id)

    @PutMapping("/user/update/job")
    fun setJobToUser(@RequestBody job: UserJob) {
        userService.setUserJob(job.userId, job.userJob)
    }

    @PutMapping("/user/update/city")
    fun setCityToUser(@RequestBody userCity: UserCity) {
        userService.setUserCity(userCity.userId, userCity.cityName)
    }

    @PutMapping("/user/update/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userParams: User) {
        userService.updateUserInfo(id, userParams)
    }

    @PutMapping("/user/update/achievement")
    fun updateUserAchievements(@RequestBody userAchievement: UserAchievement) {
        userService.setUserAchievement(userAchievement)
    }

    @PutMapping("/user/update/tag")
    fun updateUserTags(@RequestBody userTag: UserTag) {
        val tag = mutableListOf<String>()
        for (i in userTag.tag) {
            tag.add(i.title)
        }
        userService.updateUserTag(userTag.id, tag)
    }

    @PostMapping("/auth/log")
    fun authorizeNewUser(@RequestBody user: UserLoginParams): Response =
        userService.authorisation(user)


    @PostMapping("/auth/reg")
    fun registration(@RequestBody user: UserLoginParams): Response =
        userService.registration(user.login, user.pass)

    //Методы, которые относятся к Post

    @GetMapping("/post/{id}")
    fun getPostById(@PathVariable id: Int): Post {
        val mainInfoPost = postService.getById(id)
        val postTeam = postService.getPostTeamById(id)
        val postMark = postService.getPostMarkById(id)
        val postTag = postService.getPostTagById(id)
        return Post(
            id = id,
            title = mainInfoPost.title,
            icon = mainInfoPost.icon,
            description = mainInfoPost.description,
            creatorLogin = mainInfoPost.creatorLogin,
            body = mainInfoPost.body,
            team = postTeam.team,
            posMark = postMark.posMarkCount,
            negMark = postMark.negMarkCount,
            tagList = postTag.tagList,
        )
    }

    @GetMapping("/post/team/{id}")
    fun getPostTeamById(@PathVariable id: Int): PostTeam =
        postService.getPostTeamById(id)

    @GetMapping("/post/mark/{id}")
    fun getPostMarkById(@PathVariable id: Int): PostMark =
        postService.getPostMarkById(id)

    @GetMapping("/post/tag/{id}")
    fun getPostTagById(@PathVariable id: Int): PostTag =
        postService.getPostTagById(id)

    @GetMapping("/post/related/{id}")
    fun getRelatedPostById(@PathVariable id: Int): RelatedPost =
        postService.getRelatedPost(id)

    @PutMapping("/post/update/mark")
    fun updateMark(@RequestBody mark: Mark) {
        postService.markUpdate(mark.postId, mark.userId, mark.markType)
    }

    @PostMapping("/post/new")
    fun createNewPost(@RequestBody newPost: Post) =
        postService.create(newPost)

    @PutMapping("/post/update")
    fun updatePost(@RequestBody newPost: MainInfoPost) =
        postService.updateMainInfoPost(newPost)

    @PutMapping("/post/update/tag")
    fun updatePostTag(@RequestBody newPost: PostTag) {
        postService.updatePostTag(newPost)
    }

    @PutMapping("/post/update/related")
    fun updateRelatedPost(@RequestBody relatedPost: RelatedPost) {
        postService.updateRelatedPost(relatedPost)
    }

    @DeleteMapping("/post/delete/{id}")
    fun deletePostById(@PathVariable id: Int) =
        postService.deleteById(id)

    // Методы, которые относятся к Team
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

    @GetMapping("/tag")
    fun getAllTag() =
        tagService.getAllTag()

}