package com.cuidadoanimal.petcare.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.data.database.entities.Article
import com.cuidadoanimal.petcare.data.database.entities.ArticleJOINTag
import com.cuidadoanimal.petcare.data.database.entities.Tag

@Dao
interface ArticleJOINTagDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(at:ArticleJOINTag)

    @Query("SELECT * FROM article_x_tag")
    fun getAllArticleJOINTags():LiveData<List<ArticleJOINTag>>

    @Query("SELECT * FROM Article INNER JOIN article_x_tag ON Article.idArticle = article_x_tag.articleID WHERE article_x_tag.tagID=:articleID")
    fun getArticleOfTags(articleID:Int): LiveData<List<Article>>

    @Query("SELECT * FROM Tag INNER JOIN article_x_tag ON Tag.idTag = article_x_tag.tagID WHERE article_x_tag.tagID=:tagID")
    fun getTagsOfArticle(tagID:Int): LiveData<List<Tag>>

}