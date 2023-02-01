package com.example.TeamFinder.repository.TagRepository

import com.example.TeamFinder.model.Tag.TagModel

interface TagRepository {

    fun getByTitle(title: String): TagModel

    fun getById(id: Int): TagModel

    fun setByIdAndTitle(id: Int, subjectId: Int, title: String)

    fun getAllTag(): List<TagModel>
}