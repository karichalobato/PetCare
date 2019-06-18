package com.cuidadoanimal.petcare.Database.EntitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.Database.Entities.Article
import com.cuidadoanimal.petcare.Database.Entities.ArticleJOINTag
import com.cuidadoanimal.petcare.Database.Entities.Tag

@Dao
interface ArticleJOINTagDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(at:ArticleJOINTag)

    @Query("SELECT * FROM ArticleJOINTag")
    fun getAllArticleJOINTags():LiveData<List<ArticleJOINTag>>

    @Query("SELECT * FROM Article INNER JOIN ArticleJOINTag ON Article.idArticle = ArticleJOINTag.articleID WHERE ArticleJOINTag.tagID=:articleID")
    fun getArticleOfTags(articleID:Int): LiveData<List<Article>>

    @Query("SELECT * FROM Tag INNER JOIN ArticleJOINTag ON Tag.idTag = ArticleJOINTag.tagID WHERE ArticleJOINTag.tagID=:tagID")
    fun getTagsOfArticle(tagID:Int): LiveData<List<Tag>>

}