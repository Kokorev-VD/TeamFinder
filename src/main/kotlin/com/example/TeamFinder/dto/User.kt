package dto

data class User(
    val id: Int = -2,
    val login: String = "",
    val password: String = "",
    val tg: String = "",
    val description: String = "",
    val role: String = "",
    val imageId: Int = 0,
    val access: Boolean = false,
    val tags: List<String> = listOf(),
)
