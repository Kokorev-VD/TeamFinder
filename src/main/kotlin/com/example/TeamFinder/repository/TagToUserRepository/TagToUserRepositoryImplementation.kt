package com.example.TeamFinder.repository.TagToUserRepository

import com.example.TeamFinder.dto.User.UserTag
import com.example.TeamFinder.model.TagToUser.TagToUserModel
import com.example.TeamFinder.repository.TagRepository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
@Repository
class TagToUserRepositoryImplementation(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    @Autowired private val tagRepository: TagRepository,
) : TagToUserRepository {
    override fun getStringTagsByUserId(userId: Int): UserTag {
        val res: MutableList<String> = mutableListOf()
        for (i in getTagsByUserId(userId)) {
            res.add(tagRepository.getById(i.tagId).title)
        }
        return UserTag(userId, res)
    }

    override fun getTagsByUserId(userId: Int): List<TagToUserModel> =
        jdbcTemplate.query(
            "select * from tagToUserTable where userId = :userId",
            mapOf(
                "userId" to userId
            ),
            ROW_MAPPER
        )

    override fun setTagByUserIdAndTagTitle(userId: Int, tagTitle: String) {
        val tagId: Int = tagRepository.getByTitile(tagTitle).id
        jdbcTemplate.update(
            "insert into tagToUserTable (tagId, userId) values (:tagId, :userId)",
            mapOf(
                "tagId" to tagId,
                "userId" to userId,
            )
        )
    }

    override fun deleteByUserIdAndTagTitle(userId: Int, tagTitle: String) {
        val tagId: Int = tagRepository.getByTitile(tagTitle).id
        jdbcTemplate.update(
            "delete from tagToUserTable where tagId = :tagId and userId = :userId",
            mapOf(
                "tagId" to tagId,
                "userId" to userId,
            )
        )
    }

    override fun deleteAllByUserId(userId: Int) {
        jdbcTemplate.update(
            "delete from tagToUserTable where userId = :userId",
            mapOf(
                "userId" to userId,
            )
        )
    }

    override fun setListTagByUserId(userId: Int, tagTitle: List<String>) {
        for (i in tagTitle) {
            setTagByUserIdAndTagTitle(userId, i)
        }
    }

    override fun update(userId: Int, tagTitle: List<String>) {
        deleteAllByUserId(userId)
        setListTagByUserId(userId, tagTitle)
    }


    private companion object {
        val ROW_MAPPER = RowMapper<TagToUserModel> { rs, _ ->
            TagToUserModel(
                tagId = rs.getInt("tagId"),
                userId = rs.getInt("userId"),
            )
        }
    }
}