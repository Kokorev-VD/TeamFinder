package com.example.TeamFinder.service.Post

import com.example.TeamFinder.repository.PostRepository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostServiceImplementation (
    @Autowired private val postRepository: PostRepository
): PostService {
//
//    override fun getById(id: Int): Post =
//        postRepository.findById(id)?.toDto() ?: Post(
//            id = -2,
//            creatorId = -1,
//            title = "",
//            body = "",
//            pos_mark = 0,
//            neg_mark = 0,
//        )
//
//    override fun markUpdate(id: Int, markChange: Int, markType: String) {
//        postRepository.markUpdate(id, markChange, markType)
//    }
//
//    override fun getByCreator(creator: Int): List<Post> =
//        postRepository.findByCreator(creator).map { it.toDto() }
//
//    override fun findLastId(): Post =
//        postRepository.findLastId()?.toDto()!!
//
//
//    override fun create(newPost: Post): Int {
//        postRepository.create(
//            creator = newPost.creatorId,
//            header = newPost.title,
//            body = newPost.body,
//        )
//        return 100
//    }
//
//    override fun update(id: Int, newPost: Post): Int =
//        postRepository.update(id, newPost.toModel())
//
//    override fun deleteById(id: Int): Int =
//        postRepository.deleteById(id)
//
//    private fun PostModel.toDto() = Post(
//        id = id,
//        creatorId = creatorId,
//        title = title,
//        body = body,
//        pos_mark = pos_mark,
//        neg_mark = neg_mark,
//    )
//
//    private fun Post.toModel() = PostModel(
//        id = id,
//        creatorId = creatorId,
//        title = title,
//        body = body,
//        pos_mark = pos_mark,
//        neg_mark = neg_mark,
//    )
}