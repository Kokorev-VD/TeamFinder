package service

import com.example.TeamFinder.dto.ChangeableUserParams
import com.example.TeamFinder.dto.LoginUserParams
import dto.User

interface UserService {

    fun getAll():List<User>

    fun getById(id: Int): Any

    fun findByLogin(login: String): User

    fun findLastId(): Int

    fun create(user: User): Int

    fun update(userParams: ChangeableUserParams): Int

    fun authorisation(loginUserParams: LoginUserParams): Int


}