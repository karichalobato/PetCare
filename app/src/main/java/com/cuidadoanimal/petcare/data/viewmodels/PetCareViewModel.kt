package com.cuidadoanimal.petcare.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.cuidadoanimal.petcare.data.database.entities.VaccineApplication
import com.cuidadoanimal.petcare.data.repositories.PetCareRepository

class PetCareViewModel(val app: Application) : AndroidViewModel(app) {

    val TAG = "FIRESTORE_VIEW_MODEL"
    private val repository: PetCareRepository = PetCareRepository()

    var database = repository.db

    /** Guardar usuario*/
    fun insertUser(user: User) = repository.insertUser(user)

    /** Obtener usuario actual */
    fun getUser() = repository.getUser()

    fun user() = repository.firebaseUser

    /** Guardar mascota*/
    fun insertPet(pet: Pet) = repository.insertPet(pet)

    /** Obtener mascotas*/
    fun getAllPets() = repository.getAllPets()

    /** Obtener mascota específica */
    fun getPetReference(petName: String) = repository.getPetReference(petName)

    /** Guardar vacuna*/
    fun insertVaccine(petName: String, vaccine: Vaccine) = repository.insertVaccine(petName, vaccine)

    /** Obtener vacunas*/
    fun getAllVaccines(petName: String) = repository.getAllVaccines(petName)

    /** Obtener vacuna específica */
    fun getVaccine(petName: String, vaccineName: String) = repository.getVaccine(petName, vaccineName)

    /** Guardar aplicación */
    fun insertVaccineApplication(petName: String, vaccineName: String, vaccineApplication: VaccineApplication) =
        repository.insertVaccineApplication(petName, vaccineName, vaccineApplication)

    /** Obtener aplicaciones */
    fun getAllVaccineApplications(petName: String, vaccineName: String) =
        repository.getAllVaccineApplications(petName, vaccineName)

    /** Obtener aplicación específica */
    fun getVaccineApplication(petName: String, vaccineName: String, applicationDate: String) =
        repository.getVaccineApplication(petName, vaccineName, applicationDate)

    /** Obtener especies */
    fun getSpecies() = repository.getSpecies()

    /** Obtener razas de perro */
    fun getDogBreeds() = repository.getDogBreeds()

    /** Obtener razas de caballo */
    fun getHorseBreeds() = repository.getHorseBreeds()

    /** Obtener razas de gato */
    fun getCatBreeds() = repository.getCatBreeds()
}