package com.cuidadoanimal.petcare.database.entitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.database.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE name==:name")
    fun getUserByName(name: String): LiveData<List<User>>

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

    @Query("UPDATE User SET Profile_Picture = :profile_picture  WHERE Username = :username")
    fun setUserImage(username: String,profile_picture:String)

    @Query("DELETE FROM User")
    suspend fun deleteUsers()

    @Query("DELETE FROM User WHERE idUser==:idUser")
    suspend fun deleteUserByID(idUser: Int): LiveData<List<User>>

}