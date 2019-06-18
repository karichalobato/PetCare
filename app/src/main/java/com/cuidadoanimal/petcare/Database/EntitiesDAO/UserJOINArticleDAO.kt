package com.cuidadoanimal.petcare.Database.EntitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.Database.Entities.Article
import com.cuidadoanimal.petcare.Database.Entities.User
import com.cuidadoanimal.petcare.Database.Entities.UserJOINArticle

@Dao
interface UserJOINArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ua:UserJOINArticle)

    @Query("SELECT * FROM ArticleJOINTag")
    fun getAllUserJOINArticles():LiveData<List<UserJOINArticle>>

    @Query("SELECT * FROM User INNER JOIN UserJOINArticle ON User.idUser = UserJOINArticle.userID WHERE UserJOINArticle.articleID=:userID")
    fun getUserOfArticles(userID:Int): LiveData<List<User>>

    @Query("SELECT * FROM Article INNER JOIN UserJOINArticle ON Article.idArticle = UserJOINArticle.articleID WHERE UserJOINArticle.articleID=:articleID")
    fun getArticlesOfUsers(articleID:Int): LiveData<List<Article>>
}