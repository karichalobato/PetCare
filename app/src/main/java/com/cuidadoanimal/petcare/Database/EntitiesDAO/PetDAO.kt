package com.cuidadoanimal.petcare.Database.EntitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.Database.Entities.Pet

@Dao
interface PetDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM Pet WHERE name==:name")
    fun geyPetByName(name:String): LiveData<List<Pet>>

    @Query("UPDATE Pet SET Pet_Photo = :pet_photo  WHERE Name = :name")
    fun setPetImage(name:String,pet_photo:String)

    @Query("SELECT * FROM Pet")
    fun getAllPets(): LiveData<List<Pet>>

    //TODO BORRA SEGUN EL ID DE LA MASCOTA
    @Query("DELETE FROM Pet WHERE idPet==:idPet")
    fun deletePetByID(idPet: Int): LiveData<List<Pet>>

    @Query("DELETE FROM Pet")
    fun deletePets()
}