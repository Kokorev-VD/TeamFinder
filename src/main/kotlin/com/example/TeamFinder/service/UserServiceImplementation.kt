package com.example.TeamFinder.service

import com.example.TeamFinder.dto.ChangeableUserParams
import com.example.TeamFinder.dto.LoginUserParams
import com.example.TeamFinder.model.UserModel
import com.example.TeamFinder.repository.UserRepository
import dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import service.UserService


@Service("User")
class UserServiceImplementation(
    @Autowired private val userRepository: UserRepository,
): UserService {
    override fun getAll(): List<User> = userRepository.getAll()
        .map{ it.toDto() }

    override fun getById(id: Int): User =
        userRepository.findById(id)
            ?.toDto() ?: User()

    override fun findByLogin(login: String): User =
        userRepository.findByLogin(login)
            ?.toDto() ?: User()

    override fun findLastId(): Int =
        userRepository.findLastId()!!.id

    override fun create(user: User): Int {
        if (userRepository.create(user.login, user.password, user.tg, user.description, user.role, user.imageId) == 200)
            return 200
        return 100
    }


    override fun update(userParams: ChangeableUserParams): Int {
        val id = userParams.id
        if (getById(id).login == userParams.login) {
            userRepository.update(id, userParams.login, userParams.tg, userParams.description, userParams.imageId)
            return 100
        }
        return 200
    }

    override fun authorisation(loginUserParams: LoginUserParams): Int {
        if (findByLogin(loginUserParams.login).id == -2) {
            return 200
        }
        if (findByLogin(loginUserParams.login).password != loginUserParams.password) {
            return 201
        }
        return 100
    }

    private fun UserModel.toDto() = User(
        id = id,
        login = login,
        password = password,
        tg = tg,
        description = description,
        role = role,
        imageId = imageId,
    )
}