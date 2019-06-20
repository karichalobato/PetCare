package com.cuidadoanimal.petcare.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "article_x_tag",
    primaryKeys = arrayOf("articleID", "tagID"),
    foreignKeys = [ForeignKey(
        entity = Article::class,
        parentColumns = ["idArticle"], childColumns = ["articleID"]
    ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["idTag"], childColumns = ["tagID"]
        )]
)

data class ArticleJOINTag(var articleID: Int, var tagID: Int)