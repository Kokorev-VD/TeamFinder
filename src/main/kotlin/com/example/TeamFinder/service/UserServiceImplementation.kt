package com.example.TeamFinder.service

import com.example.TeamFinder.model.UserModel
import com.example.TeamFinder.repository.UserRepository
import dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import service.UserService
import java.lang.RuntimeException


@Service
class UserServiceImplementation(
    @Autowired private val userRepository: UserRepository
): UserService {
    override fun getAll(): List<User> = userRepository.getAll()
        .map{ it.toDto() }

    override fun getById(id: Int): User =
        userRepository.findById(id)
            ?.toDto() ?:
            throw RuntimeException("User with id = $id not found!")

    override fun findByLogin(login: String): User =
        userRepository.findByLogin(login)
            ?.toDto() ?:
        User(-2, "", "")

    override fun findLastId(): Int =
        userRepository.findLastId()!!.id

    override fun create(user: User): Int  =
        userRepository.create(user.login, user.password)


    override fun update(id: Int, user: User) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    private fun UserModel.toDto() = User(
        id = id,
        login = login,
        password = password,
    )
}