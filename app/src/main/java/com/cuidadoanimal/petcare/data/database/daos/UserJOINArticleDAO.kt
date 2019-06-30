package com.cuidadoanimal.petcare.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.data.database.entities.Article
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.database.entities.UserJOINArticle

@Dao
interface UserJOINArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ua: UserJOINArticle)

    @Query("SELECT * FROM UserJOINArticle")
    fun getAllUserJOINArticles(): LiveData<List<UserJOINArticle>>

    @Query("SELECT * FROM User INNER JOIN UserJOINArticle ON User.idUser = UserJOINArticle.idUser WHERE UserJOINArticle.articleID=:idUser")
    fun getUserOfArticles(idUser: Int): LiveData<List<User>>

    @Query("SELECT * FROM Article INNER JOIN UserJOINArticle ON Article.articleID = UserJOINArticle.articleID WHERE UserJOINArticle.articleID=:articleID")
    fun getArticlesOfUsers(articleID: Int): LiveData<List<Article>>
}