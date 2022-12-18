package com.example.TeamFinder.repository.TagRepository

import com.example.TeamFinder.model.Tag.TagModel

interface TagRepository {

    fun getByTitile(title: String): TagModel

    fun getById(id: Int): TagModel

}