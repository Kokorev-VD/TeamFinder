package com.example.TeamFinder.service.Tag

import com.example.TeamFinder.dto.Tag.Tag
import com.example.TeamFinder.repository.TagRepository.TagRepository
import com.example.TeamFinder.repository.TagRepository.TagSubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagServiceImplementation(
    @Autowired private val tagRepository: TagRepository,
    @Autowired private val tagSubjectRepository: TagSubjectRepository,
) : TagService {

    override fun getAllTag(): List<Tag> {
        val tagList = tagRepository.getAllTag()
        val res = mutableListOf<Tag>()
        for (tag in tagList) {
            res.add(Tag(tag.title, tagSubjectRepository.getTagSubjectById(tag.subjectId).subject))
        }
        return res
    }

}