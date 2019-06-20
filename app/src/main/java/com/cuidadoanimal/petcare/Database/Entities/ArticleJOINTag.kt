package com.cuidadoanimal.petcare.Database.Entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "article_x_tag",
    primaryKeys = arrayOf("articleID","tagID"),
    foreignKeys = [ForeignKey(entity = Article::class,
        parentColumns = ["idArticle"], childColumns = ["id_article"]),
        ForeignKey(entity = Tag::class,
            parentColumns = ["idTag"],childColumns = ["id_tag"])])

data class ArticleJOINTag (var articleID:Int, var tagID:Int)