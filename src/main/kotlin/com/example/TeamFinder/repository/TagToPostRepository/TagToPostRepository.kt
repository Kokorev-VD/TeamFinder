package com.example.TeamFinder.repository.TagToPostRepository

import com.example.TeamFinder.model.TagToPost.TagToPostModel

interface TagToPostRepository {

    fun getTagsByPostId(postId: Int): List<TagToPostModel>

    fun setTagByPostIdAndTagTitle(postId: Int, tagTitle: String)

    fun deleteByPostIdAndTagTitle(postId: Int, tagTitle: String)

}