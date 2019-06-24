package com.cuidadoanimal.petcare.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.cuidadoanimal.petcare.data.database.entities.VaccineJOINPet

@Dao
interface VaccineJOINPetDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vp:VaccineJOINPet)

    @Query("SELECT * FROM VaccineJOINPet")
    fun getAllVaccineJOINPets():LiveData<List<VaccineJOINPet>>

    @Query("SELECT * FROM Vaccine INNER JOIN VaccineJOINPet ON Vaccine.idVaccine = VaccineJOINPet.vacunaID WHERE VaccineJOINPet.mascotaID=:vacunaID")
    fun getVaccineOfPets(vacunaID:Int): LiveData<List<Vaccine>>

    @Query("SELECT * FROM Pet INNER JOIN VaccineJOINPet ON Pet.idPet = VaccineJOINPet.mascotaID WHERE VaccineJOINPet.mascotaID=:mascotaID")
    fun getPetsOfVaccines(mascotaID:Int): LiveData<List<Pet>>

    //LUEGO DE HABER SELECCIONADO UNA MASCOTA Y VER SUS VACUNAS SE PODRA FILTRAR SEGUN UN MES SELECCIONADO.
}