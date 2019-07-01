package com.cuidadoanimal.petcare.data.viewmodels

import androidx.lifecycle.ViewModel
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.database.entities.Application
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.cuidadoanimal.petcare.data.repositories.PetCareRepository

class PetCareViewModel() : ViewModel() {

    val TAG = "FIRESTORE_VIEW_MODEL"
    var repository = PetCareRepository()

    /** Guardar usuario*/
    fun insertUser(user: User) = repository.insertUser(user)

    /** Obtener usuario actual */
    fun getUser() = repository.getUser()

    /** Guardar mascota*/
    fun insertPet(pet: Pet) = repository.insertPet(pet)

    /** Obtener mascotas*/
    fun getAllPets() = repository.getAllPets()

    /** Obtener mascota específica */
    fun getPet(petName: String) = repository.getPet(petName)

    /** Guardar vacuna*/
    fun insertVaccine(petName: String, vaccine: Vaccine) = repository.insertVaccine(petName, vaccine)

    /** Obtener vacunas*/
    fun getAllVaccines(petName: String) = repository.getAllVaccines(petName)

    /** Obtener vacuna específica */
    fun getVaccine(petName: String, vaccineName: String) = repository.getVaccine(petName, vaccineName)

    /** Guardar aplicación */
    fun insertApplication(petName: String, vaccineName: String, application: Application) =
        repository.insertApplication(petName, vaccineName, application)

    /** Obtener aplicaciones */
    fun getAllApplications(petName: String, vaccineName: String) = repository.getAllApplications(petName, vaccineName)

    /** Obtener aplicación específica */
    fun getApplication(petName: String, vaccineName: String, applicationDate: String) =
        repository.getApplication(petName, vaccineName, applicationDate)
}