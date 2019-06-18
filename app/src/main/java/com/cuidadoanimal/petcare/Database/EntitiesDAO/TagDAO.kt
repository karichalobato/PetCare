package com.cuidadoanimal.petcare.Database.EntitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.Database.Entities.Tag

@Dao
interface TagDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag)

    @Query("SELECT * FROM Tag WHERE name==:name")
    fun getTagByName(name:String): LiveData<List<Tag>>

    @Query("SELECT * FROM Tag")
    fun getAllTags(): LiveData<List<Tag>>

    @Query("DELETE FROM Tag WHERE idTag==:idTag")
    fun deleteTagByID(idTag:Int): LiveData<List<Tag>>

    @Query("DELETE FROM Tag")
    fun deleteTags()
}