package com.example.TeamFinder.service.Post

import com.example.TeamFinder.dto.Post.Post
import com.example.TeamFinder.dto.User.UserProfile
import com.example.TeamFinder.repository.MarkRepository.MarkRepository
import com.example.TeamFinder.repository.PostRepository.PostRepository
import com.example.TeamFinder.repository.PostRepository.PostToPostRepository
import com.example.TeamFinder.repository.TagToPostRepository.TagToPostRepository
import com.example.TeamFinder.repository.TeamRepository.TeamRepository
import com.example.TeamFinder.repository.UserCreatorToPostRepository.UserCreatorToPostRepository
import com.example.TeamFinder.repository.UserRepository.UserLoginParamsRepository
import com.example.TeamFinder.service.User.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostServiceImplementation(
    @Autowired private val postRepository: PostRepository,
    @Autowired private val userLoginParamsRepository: UserLoginParamsRepository,
    @Autowired private val teamRepository: TeamRepository,
    @Autowired private val markRepository: MarkRepository,
    @Autowired private val tagToPostRepository: TagToPostRepository,
    @Autowired private val userService: UserService,
    @Autowired private val postToPostRepository: PostToPostRepository,
    @Autowired private val userCreatorToPostRepository: UserCreatorToPostRepository,
) : PostService {

    override fun getById(id: Int): Post {
        val thisPost = postRepository.findById(id)
        val teamUserId = teamRepository.getByTeamId(id)
        val team = mutableListOf<UserProfile>()
        for (i in teamUserId) {
            team.add(userService.getById(i.userId))
        }
        return Post(
            title = thisPost.title,
            creatorLogin = userLoginParamsRepository.getById(
                userCreatorToPostRepository.getUserCreatorToPostModelByPostId(
                    id
                ).userId
            ).login,
            body = thisPost.body,
            team = team,
            posMark = markRepository.getPosMarksByPostId(id),
            negMark = markRepository.getNegMarksByPostId(id),
            tagList = tagToPostRepository.getListTagsByPostId(id),
            derivedPosts = postToPostRepository.getDerivedListMainInfoPost(id),
            basedPosts = postToPostRepository.getBasedListMainInfoPost(id),
        )
    }


    override fun markUpdate(postId: Int, userId: Int, markType: Int) {
        if (markType == 0) {
            markRepository.deleteByPostIdAndUserId(postId, userId)
        } else {
            markRepository.update(postId, userId, markType)
        }
    }

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