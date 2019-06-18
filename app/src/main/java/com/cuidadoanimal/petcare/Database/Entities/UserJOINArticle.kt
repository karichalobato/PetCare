package com.cuidadoanimal.petcare.Database.Entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName = "UserJOINArticle",
    primaryKeys = arrayOf("userID", "articleID"),
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = ["idUser"], childColumns = ["id_user"]),
        ForeignKey(entity = Article::class,parentColumns = ["idArticle"],
            childColumns = ["id_article"])])
data class UserJOINArticle (var userID:Int, var articleID:Int)