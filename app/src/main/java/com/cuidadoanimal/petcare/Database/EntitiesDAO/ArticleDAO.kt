package com.cuidadoanimal.petcare.Database.EntitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.Database.Entities.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article): LiveData<List<Article>>

    @Query("SELECT * FROM Article WHERE title==:title")
    fun getArticleByTitle(title:String): LiveData<List<Article>>

    @Query("SELECT * FROM Article")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("DELETE FROM Article WHERE idArticle==:idArticle")
    fun deleteArticleByID(idArticle:Int): LiveData<List<Article>>

    @Query("DELETE FROM Article")
    fun deleteArticles()
}