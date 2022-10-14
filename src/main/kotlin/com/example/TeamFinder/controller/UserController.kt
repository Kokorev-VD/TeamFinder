package com.example.TeamFinder.controller

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
class UserController(
    private val userService: UserService
) {

    private val testLogin1:String = "Vitya"
    private val testPassword1:String = "1234"
    private val testLogin2 = "Kolya"
    private val testPassword2:String = "4321"

//    @GetMapping
//    fun test(): String{
//        return "hello";
//    }

//    @GetMapping
//    fun getAll(): List<User> = userService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): User {
        return userService.getById(id)
    }
    
    @PostMapping("/reg")
    fun create(@RequestBody user: User): String {
        var m = userService.create(user)
        if (m == -2){
            return "Something go wrong..."
        }
        return "You are registrated, ${user.login}"
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody user: User){
        TODO()
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int) {
        userService.deleteById(id)
    }

    @PostMapping("/auth")
    fun authorisation(@RequestBody user: User): String {
        if(userService.findByLogin(user.login)
                .password == user.password && user.login != ""){
            return "Hello, ${user.login}!"
        }
        return "Wrong login or password"
    }
}