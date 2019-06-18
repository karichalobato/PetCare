package com.cuidadoanimal.petcare.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Url

@Entity(tableName = "Article")
data class Article (

    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Description")
    val description: String,
    @ColumnInfo(name = "Url")
    val url: Url
)

{
    @PrimaryKey(autoGenerate = true)
    var idArticle: Int = 0
}