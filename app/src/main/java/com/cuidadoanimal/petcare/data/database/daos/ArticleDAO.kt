package com.cuidadoanimal.petcare.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.data.database.entities.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM Article WHERE title==:title")
    fun getArticleByTitle(title: String): LiveData<List<Article>>

    @Query("SELECT * FROM Article")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("DELETE FROM Article WHERE articleID==:articleID")
    suspend fun deleteArticleByID(articleID: Int)

    @Query("DELETE FROM Article")
    suspend fun deleteArticles()
}