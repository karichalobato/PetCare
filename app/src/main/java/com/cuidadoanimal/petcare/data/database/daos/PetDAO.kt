package com.cuidadoanimal.petcare.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.data.database.entities.Pet

@Dao
interface PetDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM Pet WHERE name==:name")
    fun geyPetByName(name: String): LiveData<List<Pet>>

    @Query("SELECT * FROM Pet")
    fun getAllPets(): LiveData<List<Pet>>

    //TODO BORRA SEGUN EL ID DE LA MASCOTA
    @Query("DELETE FROM Pet WHERE idPet==:idPet")
    suspend fun deletePetByID(idPet: Int)

    @Query("DELETE FROM Pet")
    suspend fun deletePets()
}