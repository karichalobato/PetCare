package com.cuidadoanimal.petcare.data.repositories

import com.cuidadoanimal.petcare.data.AppConstants.Companion.APPLICATIONS_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.AppConstants.Companion.PETS_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.AppConstants.Companion.USERS_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.AppConstants.Companion.VACCINES_COLLECTION_NAME
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.cuidadoanimal.petcare.data.database.entities.VaccineApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PetCareRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var db = FirebaseFirestore.getInstance()

    var firebaseUser = FirebaseAuth.getInstance().currentUser

    /** Guardar usuario*/
    fun insertUser(user: User) = getUser().set(user)

    /** Obtener usuario actual */
    fun getUser() = db
        .collection(USERS_COLLECTION_NAME)
        .document(firebaseUser!!.email.toString())

    /** Guardar mascota*/
    fun insertPet(pet: Pet) = getPet(pet.pet_name!!)
        .set(pet)

    /** Obtener mascotas*/
    fun getAllPets() = getUser()
        .collection(PETS_COLLECTION_NAME)

    /** Obtener mascota específica */
    fun getPet(petName: String) = getAllPets()
        .document(petName)

    /** Guardar vacuna*/
    fun insertVaccine(petName: String, vaccine: Vaccine) = getVaccine(petName, vaccine.vaccine_name!!)
        .set(vaccine)

    /** Obtener vacunas*/
    fun getAllVaccines(petName: String) = getPet(petName)
        .collection(VACCINES_COLLECTION_NAME)

    /** Obtener vacuna específica */
    fun getVaccine(petName: String, vaccineName: String) = getAllVaccines(petName)
        .document(vaccineName)

    /** Guardar aplicación */
    fun insertVaccineApplication(petName: String, vaccineName: String, vaccineApplication: VaccineApplication) =
        getAllVaccineApplications(petName, vaccineName)
            .add(vaccineApplication)

    /** Obtener aplicaciones */
    fun getAllVaccineApplications(petName: String, vaccineName: String) = getVaccine(petName, vaccineName)
        .collection(APPLICATIONS_COLLECTION_NAME)

    /** Obtener aplicación específica */
    fun getVaccineApplication(petName: String, vaccineName: String, applicationDate: String) =
        getAllVaccineApplications(petName, vaccineName).whereEqualTo("application_date", applicationDate)

}
