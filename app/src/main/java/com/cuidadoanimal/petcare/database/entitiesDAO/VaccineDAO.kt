package com.cuidadoanimal.petcare.database.entitiesDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.database.entities.Vaccine

@Dao
interface VaccineDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVaccine(vaccine: Vaccine)

    @Query("SELECT * FROM Vaccine WHERE name==:name")
    fun getVaccineByName(name: String): LiveData<List<Vaccine>>

    @Query("SELECT * FROM Vaccine")
    fun getAllVaccines(): LiveData<List<Vaccine>>

    @Query("DELETE FROM Vaccine WHERE idVaccine==:idVaccine")
    suspend fun deleteVaccineByID(idVaccine: Int)

    @Query("DELETE FROM Vaccine")
    suspend fun deleteVaccines()
}