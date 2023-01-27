package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.dto.Post.MainInfoPost
import com.example.TeamFinder.model.Post.PostExtensionModel

interface PostToPostRepository {

    fun setByBasedPostIdAndDerivedPostId(basedPostId: Int, derivedPostId: Int)

    fun getByBasedPostId(basedPostId: Int): List<PostExtensionModel>

    fun getByDerivedPostId(derivedPostId: Int): List<PostExtensionModel>

    fun getBasedListMainInfoPost(id: Int): List<MainInfoPost>

    fun getDerivedListMainInfoPost(id: Int): List<MainInfoPost>

    fun deleteByBasedPostIdAndDerivedPostId(basedPostId: Int, derivedPostId: Int)

    fun deleteByBasedPostId(basedPostId: Int)

    fun deleteByDerivedPostId(derivedPostId: Int)

    fun update(basedPostId: Int, derivedPostId: Int)

}