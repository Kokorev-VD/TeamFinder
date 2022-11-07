package com.example.TeamFinder.controller

import com.example.TeamFinder.dto.ChangeableUserParams
import com.example.TeamFinder.dto.LoginUserParams
import com.example.TeamFinder.dto.Post
import com.example.TeamFinder.service.PostService
import dto.User
import org.springframework.web.bind.annotation.*
import service.UserService

@RestController
@RequestMapping
class Controller(
    private val userService: UserService,
    private val postService: PostService,
) {

    // Методы, которые относятся к User
    @GetMapping("/user")
    fun getUserById(@RequestBody id: Int): User =
        userService.getById(id)


    @PostMapping("/auth/reg")
    fun registerNewUser(@RequestBody user: User): Int {
        var m = userService.create(user)
        if (m == -2) {
            return 200
        }
        return 100
    }

    @PutMapping("/user/update")
    fun updateUserById(@RequestBody userParams: ChangeableUserParams): Int =
        userService.update(userParams)


    @PostMapping("/auth/log")
    fun authorizeNewUser(@RequestBody user: LoginUserParams): Int {
        if (userService.findByLogin(user.login)
                .password == user.password && user.login != ""
        ) {
            return 100
        }
        return 200
    }

    //Методы, которые относятся к Post

    @GetMapping("/post")
    fun getPostById(@RequestBody id: Int) =
        postService.getById(id)

    @GetMapping("/post/creator")
    fun getPostByCreator(@RequestBody creator: Int): List<Post> {
        return postService.getByCreator(creator)
    }

    @PostMapping("/post/new")
    fun createNewPost(@RequestBody newPost: Post): Int {
        postService.create(newPost)
        return 100
    }

    @PutMapping("/post/update")
    fun updatePostById(@RequestBody newPost: Post) {
        postService.update(newPost.id, newPost)
    }

    @DeleteMapping("/post/delete")
    fun deletePostById(@RequestBody id: Int) {
        postService.deleteById(id)
    }

}