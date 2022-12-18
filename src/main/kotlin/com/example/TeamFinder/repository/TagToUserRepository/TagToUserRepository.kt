package com.example.TeamFinder.repository.TagToUserRepository

import com.example.TeamFinder.dto.User.UserTag
import com.example.TeamFinder.model.TagToUser.TagToUserModel

interface TagToUserRepository {

    fun getStringTagsByUserId(userId: Int): UserTag

    fun getTagsByUserId(userId: Int): List<TagToUserModel>

    fun setTagByUserIdAndTagTitle(userId: Int, tagTitle: String)

    fun deleteByUserIdAndTagTitle(userId: Int, tagTitle: String)

    fun deleteAllByUserId(userId: Int)

    fun setListTagByUserId(userId: Int, tagTitle: List<String>)

    fun update(userId: Int, tagTitle: List<String>)
}