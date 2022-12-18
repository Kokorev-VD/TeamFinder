package com.example.TeamFinder.repository.PostRepository

import com.example.TeamFinder.model.Post.PostExtensionModel

interface PostToPostRepository {

    fun setByBasedPostIdAndDerivedPostId(basedPostId: Int, derivedPostId: Int)

    fun getByBasedPostId(basedPostId: Int): List<PostExtensionModel>

    fun getByDerivadPostId(derivedPostId: Int): List<PostExtensionModel>

}