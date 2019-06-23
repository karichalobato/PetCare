package com.cuidadoanimal.petcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Url

@Entity(tableName = "Article")
data class Article(

    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Url")
    val url: String
) {
    @PrimaryKey(autoGenerate = true)
    var idArticle: Long = 0
}