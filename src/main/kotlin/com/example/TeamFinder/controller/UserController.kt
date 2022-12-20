package com.example.TeamFinder.controller

import com.example.TeamFinder.dto.Response.Response
import com.example.TeamFinder.dto.User.UserAchievement
import com.example.TeamFinder.dto.User.UserTag
import com.example.TeamFinder.model.Mark.MarkModel
import com.example.TeamFinder.model.User.UserLoginParamsModel
import com.example.TeamFinder.model.User.UserModel
import com.example.TeamFinder.service.Post.PostService
import com.example.TeamFinder.service.User.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class Controller(
    private val userService: UserService,
    private val postService: PostService,
) {

    // Методы, которые относятся к User
    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Int): Any =
        userService.getById(id)


    @PutMapping("/user/update/model")
    fun updateUserById(@RequestBody userParams: UserModel): Response =
        userService.updateUserInfo(userParams)

    @PutMapping("/user/update/achievement")
    fun updateUserAchievements(@RequestBody userAchievement: UserAchievement) {
        userService.updateUserAchievement(userAchievement.id, userAchievement.achievement)
    }

    @PutMapping("/user/update/tag")
    fun updateUserTags(@RequestBody userTag: UserTag) {
        userService.updateUserTag(userTag.id, userTag.tag)
    }

    @PostMapping("/auth/log")
    fun authorizeNewUser(@RequestBody user: UserLoginParamsModel): Response =
        userService.authorisation(user)


    @PostMapping("/auth/reg")
    fun registration(@RequestBody user: UserLoginParamsModel) =
        userService.registration(user.login, user.pass)

    //Методы, которые относятся к Post

    @GetMapping("/post/{id}")
    fun getPostById(@PathVariable id: Int) =
        postService.getById(id)

    @PutMapping("/post/updateMark")
    fun updateMArk(@RequestBody markModel: MarkModel) {
        postService.markUpdate(markModel.postId, markModel.userId, markModel.markType)
    }
//
//    @GetMapping("/post/creator")
//    fun getPostByCreator(@RequestBody creator: Int): List<Post> =
//        postService.getByCreator(creator)
//
//    @PostMapping("/post/new")
//    fun createNewPost(@RequestBody newPost: Post): Int =
//        postService.create(newPost)
//
//    @PutMapping("/post/update")
//    fun updatePostById(@RequestBody newPost: Post) =
//        postService.update(newPost.id, newPost)
//
//
//    @DeleteMapping("/post/delete")
//    fun deletePostById(@RequestBody id: Int) =
//        postService.deleteById(id)


}