package com.example.TeamFinder.controller

import com.example.TeamFinder.service.PostService
import dto.User
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.UserService

@RestController
@RequestMapping
class Controller(
    private val userService: UserService,
    private val postService: PostService
) {

    // Методы, которые относятся к User
    @GetMapping("/user/{id}")
    fun getById(@PathVariable id: Int): User {
        return userService.getById(id)
    }
    
    @PostMapping("/reg")
    fun register(@RequestBody user: User): String {
        var m = userService.create(user)
        if (m == -2){
            return "Something go wrong..."
        }
        return "You are registered, ${user.login}"
    }

    @PutMapping("/user/{id}")
    fun update(@PathVariable id: Int, @RequestBody user: User){
        TODO()
    }

    @PostMapping("/auth")
    fun authorisation(@RequestBody user: User): String {
        if(userService.findByLogin(user.login)
                .password == user.password && user.login != ""){
            return "Hello, ${user.login}!"
        }
        return "Wrong login or password"
    }

    //Методы, которые относятся к Post



}