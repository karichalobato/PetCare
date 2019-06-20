package com.cuidadoanimal.petcare.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName = "UserJOINArticle",
    primaryKeys = arrayOf("userID", "articleID"),
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = ["idUser"], childColumns = ["userID"]),
        ForeignKey(entity = Article::class,parentColumns = ["idArticle"],
            childColumns = ["articleID"])])
data class UserJOINArticle (var userID:Int, var articleID:Int)