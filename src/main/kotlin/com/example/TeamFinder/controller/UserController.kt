package com.example.TeamFinder.controller

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
    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Int): User =
        userService.getById(id)


    @PostMapping("/reg")
    fun registerNewUser(@RequestBody user: User): String {
        var m = userService.create(user)
        if (m == -2) {
            return "Something go wrong..."
        }
        return "You are registered, ${user.login}"
    }

    @PutMapping("/user/{id}")
    fun updateUserById(@PathVariable id: Int, @RequestBody user: User) {
        TODO()
    }

    @PostMapping("/auth")
    fun authorizeNewUser(@RequestBody user: User): String {
        if (userService.findByLogin(user.login)
                .password == user.password && user.login != ""
        ) {
            return "Hello, ${user.login}!"
        }
        return "Wrong login or password"
    }

    //Методы, которые относятся к Post

    @GetMapping("/post/id/{id}")
    fun getPostById(@PathVariable id: Int) =
        postService.getById(id)

    @GetMapping("/post/creator/{creator}")
    fun getPostByCreator(@PathVariable creator: String) =
        postService.getByCreator(creator)

    @PostMapping("/post/create_new_post")
    fun createNewPost(@RequestBody newPost: Post): String {
        postService.create(newPost)
        return "${newPost.creator}, you created new post ${newPost.header}"
    }


}