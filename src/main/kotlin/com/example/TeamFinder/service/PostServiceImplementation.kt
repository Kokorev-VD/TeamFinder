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

    override fun getById(id: Int): Post =
        postRepository.findById(id)?.toDto() ?: Post(
            id = -2,
            creator = -1,
            header = "",
            body = "",
            pos_mark = 0,
            neg_mark = 0,
        )

    override fun markUpdate(id: Int, markChange: Int, markType: String) {
        postRepository.markUpdate(id, markChange, markType)
    }

    override fun getByCreator(creator: Int): List<Post> =
        postRepository.findByCreator(creator).map { it.toDto() }

    override fun findLastId(): Post =
        postRepository.findLastId()?.toDto()!!


    override fun create(newPost: Post): Int =
        postRepository.create(
            creator = newPost.creator,
            header = newPost.header,
            body = newPost.body,
        )

    override fun update(id: Int, newPost: Post) {
        postRepository.update(id, newPost.toModel())
    }

    override fun deleteById(id: Int) {
        postRepository.deleteById(id)
    }

    private fun PostModel.toDto() = Post(
        id = id,
        creator = creator,
        header = header,
        body = body,
        pos_mark = pos_mark,
        neg_mark = neg_mark,
    )

    private fun Post.toModel() = PostModel(
        id = id,
        creator = creator,
        header = header,
        body = body,
        pos_mark = pos_mark,
        neg_mark = neg_mark,
    )
}