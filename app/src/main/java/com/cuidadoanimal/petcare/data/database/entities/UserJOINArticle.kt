package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
        tableName = "UserJOINArticle",
        primaryKeys = ["idUser", "articleID"],
        foreignKeys = [
            ForeignKey(
                    entity = User::class,
                    parentColumns = ["idUser"],
                    childColumns = ["idUser"]
            ),
            ForeignKey(
                    entity = Article::class,
                    parentColumns = ["articleID"],
                    childColumns = ["articleID"]
            )]
)
data class UserJOINArticle(
    var idUser: Long,
    var articleID: Long)