package com.cuidadoanimal.petcare.gui.activities

//import androidx.lifecycle.ViewModelProviders
//import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Application
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.gui.fragments.NewPet
import com.cuidadoanimal.petcare.gui.fragments.NewVaccine
import com.cuidadoanimal.petcare.gui.fragments.NewVaccineApplication
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
        AppCompatActivity(),
        NewPet.NewPetListener,
        NewVaccineApplication.NewVaccineApplicationListener,
        NewVaccine.NewVaccineListener {

    private var doubleBackToExitPressedOnce = false

    private val auth = FirebaseAuth.getInstance()

//    lateinit var viewModel: PetCareViewModel

    /* Obtener instancia de base de datos */
    private var myDB = FirebaseFirestore.getInstance()

    override fun insertApplication(petName: String, vaccineName: String, year: String, month: String, day: String) {
        var appDate = "$year/$month/$day"

        myDB
                .collection("users")
                .document(auth.currentUser?.email.toString())
                // HASTA ACA FUNCION USER()
                .collection("pets")
                .document(petName)
                .collection("vaccines")
                .document(vaccineName)
                .collection("applications")
                .add(Application(
                        application_date = appDate,
                        application_day = day.toInt(),
                        application_month = month.toInt(),
                        application_year = year.toInt()
                ))
    }

    override fun insertVaccine(petName: String, vaccineName: String, year: String, month: String, day: String) {

        var vaccine: DocumentReference = myDB
                .collection("users")
                .document(auth.currentUser?.email.toString())
                .collection("pets")
                .document(petName)
                .collection("vaccines")
                .document(vaccineName)

        vaccine.set(
                mapOf(
                        "vaccine_name" to vaccineName
                )
        )

        insertFirstApplication(vaccine, year, month, day)
    }

    private fun insertFirstApplication(vaccine: DocumentReference, year: String, month: String, day: String) {

        var appDate = "$year/$month/$day"

        vaccine.collection("applications")
                .add(Application(
                        application_date = appDate,
                        application_day = day.toInt(),
                        application_month = month.toInt(),
                        application_year = year.toInt()
                ))

    }

    private fun saveUser() {
            /*TODO SOLO MANDAR A LLAMAR A insert(con instancia de user) + IDENTIFICAR DONDE HACER INSERT -SI ES NECESARIO INTERFAZ DE FRAGMENTO**/
        myDB
                .collection("users")
                .document(auth.currentUser?.email.toString())
                .set(User(display_name= auth.currentUser?.displayName.toString(),
                                email= auth.currentUser?.email.toString()
                        )
                )
    }

    override fun insertPet(petName: String, petBreed: String, petSex: String) {

        myDB
                .collection("users")
                .document(auth.currentUser?.email.toString())
                .collection("pets")
                .document(petName)
                .set(
                        mapOf(
                                "pet_name" to petName,
                                "pet_breed" to petBreed,
                                "pet_sex" to petSex
                        )
                )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val displayName: String? = intent?.getStringExtra("displayName")
        val email: String? = intent?.getStringExtra("email")

        if (displayName != null && email != null) saveUser()

        val navController = findNavController(nav_host_fragment)
        setupBottomNavMenu(navController)

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce || supportFragmentManager.backStackEntryCount != 0) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Presiona de nuevo para salir", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        findViewById<BottomNavigationView>(R.id.bottom_nav_view)
                .setupWithNavController(navController)
    }
}

