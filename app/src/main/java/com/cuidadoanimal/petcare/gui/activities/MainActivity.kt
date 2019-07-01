package com.cuidadoanimal.petcare.gui.activities

import androidx.lifecycle.ViewModelProviders
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.VaccineApplication
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
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

    private lateinit var info: PetCareViewModel

    override fun insertApplication(petName: String, vaccineName: String, year: String, month: String, day: String) {
        var appDate = "$year/$month/$day"

        info.insertVaccineApplication(
            petName,
            vaccineName,
            VaccineApplication(
                application_date = appDate,
                application_day = day.toInt(),
                application_month = month.toInt(),
                application_year = year.toInt()
            )
        )
    }

    override fun insertVaccine(petName: String, vaccineName: String, year: String, month: String, day: String) {

        info.insertVaccine(
            petName,
            Vaccine(
                vaccine_name = vaccineName
            )
        )

        insertApplication(petName, vaccineName, year, month, day)

    }

    private fun saveUser() {
        var user = info.user()

        info.insertUser(
            User(
                display_name = user?.displayName.toString(),
                email = user?.email.toString()
            )
        )
    }

    override fun insertPet(petName: String, petBreed: String, petSex: String) {

        info.insertPet(
            Pet(
                pet_name = petName,
                pet_breed = petBreed,
                pet_sex = petSex
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        info = ViewModelProviders.of(this).get(PetCareViewModel::class.java)

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

