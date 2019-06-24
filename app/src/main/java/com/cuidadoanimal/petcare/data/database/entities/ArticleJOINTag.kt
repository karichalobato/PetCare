package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "article_x_tag",
    primaryKeys = ["articleID", "tagID"],
    foreignKeys = [
        ForeignKey(
            entity = Article::class,
            parentColumns = ["idArticle"],
            childColumns = ["articleID"]
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["idTag"],
            childColumns = ["tagID"]
        )]
)

data class ArticleJOINTag(var articleID: Long, var tagID: Long)