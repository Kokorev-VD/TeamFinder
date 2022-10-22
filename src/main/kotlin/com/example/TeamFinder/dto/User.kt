package dto

data class User(
    val id: Int = 0,
    val login: String,
    val password: String,
    val tg: String = "",
    val description: String = "",
    val role: String = "user",
    val imageId: Int = 0,
    )
