package com.cuidadoanimal.petcare.data.repositories

import com.cuidadoanimal.petcare.data.AppConstants.Companion.APPLICATIONS_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.AppConstants.Companion.PETS_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.AppConstants.Companion.USERS_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.AppConstants.Companion.VACCINES_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.database.entities.Application
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PetCareRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var db = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser

    /** Guardar usuario*/
    private fun insertUser(user: User) = getUser().set(user)

    /** Obtener usuario actual */
    private fun getUser() = db
            .collection(USERS_COLLECTION_NAME)
            .document(user!!.email.toString())

    /** Guardar mascota*/
    private fun insertPet(pet: Pet) = getPet(pet.pet_name!!)
            .set(pet)

    /** Obtener mascotas*/
    private fun getAllPets() = getUser()
            .collection(PETS_COLLECTION_NAME)

    /** Obtener mascota específica */
    private fun getPet(petName: String) = getAllPets()
            .document(petName)

    /** Guardar vacuna*/
    private fun insertVaccine(petName: String, vaccine: Vaccine) = getVaccine(petName, vaccine.vaccine_name!!)
            .set(vaccine)

    /** Obtener vacunas*/
    private fun getAllVaccines(petName: String) = getPet(petName)
            .collection(VACCINES_COLLECTION_NAME)

    /** Obtener vacuna específica */
    private fun getVaccine(petName: String, vaccineName: String) = getAllVaccines(petName)
            .document(vaccineName)

    /** Guardar aplicación */
    private fun insertApplication(petName: String, vaccineName: String, application: Application) = getApplication(petName, vaccineName, application.application_date!!)
            .set(application)

    /** Obtener aplicaciones */
    private fun getAllApplications(petName: String, vaccineName: String) = getVaccine(petName, vaccineName)
            .collection(APPLICATIONS_COLLECTION_NAME)

    /** Obtener aplicación específica */
    private fun getApplication(petName: String, vaccineName: String, applicationDate: String) = getAllApplications(petName, vaccineName)
            .document(applicationDate)

}
