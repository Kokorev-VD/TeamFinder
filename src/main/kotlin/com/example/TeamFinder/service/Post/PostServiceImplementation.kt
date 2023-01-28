package com.example.TeamFinder.service.Post

import com.example.TeamFinder.dto.Post.*
import com.example.TeamFinder.dto.User.User
import com.example.TeamFinder.model.Post.PostModel
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

    override fun getById(id: Int): MainInfoPost {
        val thisPost = postRepository.findById(id)
        return MainInfoPost(
            id = id,
            title = thisPost.title,
            creatorLogin = userLoginParamsRepository.getById(
                userCreatorToPostRepository.getUserCreatorToPostModelByPostId(
                    id
                ).userId
            ).login,
            body = thisPost.body,
        )
    }

    override fun getPostTeamById(id: Int): PostTeam {
        val teamUserId = teamRepository.getByTeamId(id)
        val team = mutableListOf<User>()
        for (i in teamUserId) {
            team.add(userService.getById(i.userId))
        }
        return PostTeam(id, team)
    }

    override fun getPostMarkById(id: Int): PostMark =
        PostMark(id, markRepository.getPosMarksByPostId(id), markRepository.getNegMarksByPostId(id))

    override fun getPostTagById(id: Int): PostTag =
        PostTag(id, tagToPostRepository.getListTagsByPostId(id))

    override fun getRelatedPost(id: Int): RelatedPost =
        RelatedPost(
            id,
            postToPostRepository.getDerivedListMainInfoPost(id),
            postToPostRepository.getBasedListMainInfoPost(id)
        )


    override fun create(newPost: Post) {
        val id = postRepository.create(newPost.title, newPost.body)
        userCreatorToPostRepository.setUserCreatorIdToPost(
            userLoginParamsRepository.getByLogin(newPost.creatorLogin)!!.id,
            id
        )
        for (tag in newPost.tagList) {
            tagToPostRepository.setTagByPostIdAndTagTitle(id, tag)
        }
        for (basedPost in newPost.basedPosts) {
            postToPostRepository.setByBasedPostIdAndDerivedPostId(basedPost.id, id)
        }

    }

    override fun update(id: Int, newPost: Post) {
        postRepository.update(id, PostModel(id, newPost.title, newPost.body))
        tagToPostRepository.deleteByPostId(id)
        for (tag in newPost.tagList) {
            tagToPostRepository.setTagByPostIdAndTagTitle(id, tag)
        }
    }


    override fun markUpdate(postId: Int, userId: Int, markType: Int) {
        if (markType == 0) {
            markRepository.deleteByPostIdAndUserId(postId, userId)
        } else {
            markRepository.update(postId, userId, markType)
        }
    }

    override fun deleteById(id: Int) {
        tagToPostRepository.deleteByPostId(id)
        postToPostRepository.deleteByBasedPostId(id)
        postToPostRepository.deleteByDerivedPostId(id)
        markRepository.deleteByPostId(id)
        userCreatorToPostRepository.deleteByPostId(id)
        teamRepository.deleteTeamByPostId(id)
        postRepository.deleteById(id)
    }


}