package com.example.TeamFinder.service

import com.example.TeamFinder.dto.ChangeableUserParams
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
            ?.toDto() ?:
            throw RuntimeException("User with id = $id not found!")

    override fun findByLogin(login: String): User =
        userRepository.findByLogin(login)
            ?.toDto() ?: User(-2, "", "")

    override fun findLastId(): Int =
        userRepository.findLastId()!!.id

    override fun create(user: User): Int =
        userRepository.create(user.login, user.password, user.tg, user.description, user.role, user.imageId)


    override fun update(userParams: ChangeableUserParams): Int {
        val id = userParams.id
        if (getById(id).login == userParams.login || findByLogin(userParams.login).id == -2) {
            userRepository.update(id, userParams.login, userParams.tg, userParams.description, userParams.imageId)
            return 100
        }
        return 200
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
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