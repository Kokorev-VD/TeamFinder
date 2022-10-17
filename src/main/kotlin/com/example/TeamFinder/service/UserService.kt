package service

import dto.User

interface UserService {

    fun getAll():List<User>

    fun getById(id: Int): User

    fun findByLogin(login: String): User

    fun findLastId(): Int

    fun create(user: User): Int

    fun update(id: Int, user: User)

    fun deleteById(id: Int)

}