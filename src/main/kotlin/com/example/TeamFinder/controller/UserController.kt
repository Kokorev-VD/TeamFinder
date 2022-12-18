package com.example.TeamFinder.controller

import com.example.TeamFinder.dto.User.UserAchievement
import com.example.TeamFinder.dto.User.UserTag
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


//    @PostMapping("/auth/reg")
//    fun registerNewUser(@RequestBody user: User): Int =
//        userService.create(user)

    @PutMapping("/user/update/model")
    fun updateUserById(@RequestBody userParams: UserModel): Int =
        userService.updateUserInfo(userParams)

    @PutMapping("/user/update/achievement")
    fun updateUserAchievements(@RequestBody userAchievement: UserAchievement) {
        userService.updateUserAchievement(userAchievement.userId, userAchievement.achievement)
    }

    @PutMapping("/user/update/tag")
    fun updateUserTags(@RequestBody userTag: UserTag) {
        userService.updateUserTag(userTag.userId, userTag.tag)
    }

    @PostMapping("/auth/log")
    fun authorizeNewUser(@RequestBody user: UserLoginParamsModel): Int =
        userService.authorisation(user)


    @PostMapping("user/reg")
    fun registration(@RequestBody user: UserLoginParamsModel) {
        userService.registration(user.login, user.pass)
    }
    //Методы, которые относятся к Post

//    @GetMapping("/post")
//    fun getPostById(@RequestBody id: Int) =
//        postService.getById(id)
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