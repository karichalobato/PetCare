package com.cuidadoanimal.petcare.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.data.database.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE name==:name")
    fun getUserByName(name: String): LiveData<List<User>>

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>
    
    @Query("DELETE FROM User")
    suspend fun deleteUsers()

    @Query("DELETE FROM User WHERE idUser==:idUser")
    suspend fun deleteUserByID(idUser: Int)

}