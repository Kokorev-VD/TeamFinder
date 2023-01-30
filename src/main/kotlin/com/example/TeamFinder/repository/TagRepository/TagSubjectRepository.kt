package com.example.TeamFinder.repository.TagRepository

import com.example.TeamFinder.model.Tag.TagSubjectModel

interface TagSubjectRepository {

    fun setTagSubject(id: Int, subject: String)

    fun getTagSubjectById(id: Int): TagSubjectModel

}