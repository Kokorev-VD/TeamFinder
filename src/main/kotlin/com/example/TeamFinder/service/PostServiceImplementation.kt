package com.example.TeamFinder.service

import com.example.TeamFinder.dto.Post
import com.example.TeamFinder.model.PostModel
import com.example.TeamFinder.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostServiceImplementation (
    @Autowired private val postRepository: PostRepository
): PostService {

    override fun getById(id: Int): Post  =
        postRepository.findById(id)?.toDto() ?:
        Post(
            id = -2,
            creator = "",
            header = "",
            body = "",
            pos_mark = 0,
            neg_mark = 0,
        )

    override fun getByCreator(creator: String): List<Post> =
        postRepository.findByCreator(creator).map{ it.toDto() }

    override fun findLastId(): Post =
        postRepository.findLastId()?.toDto()!!


    override fun create(newPost: Post): Int =
        postRepository.create(
            creator = newPost.creator,
            header = newPost.header,
            body = newPost.body,
        )

    override fun update(id: Int, newPost: Post) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    private fun PostModel.toDto() = Post(
        id = id,
        creator = creator,
        header = header,
        body = body,
        pos_mark = pos_mark,
        neg_mark = neg_mark,
    )
}