package com.cuidadoanimal.petcare.gui.activities

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.database.entities.User
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import com.cuidadoanimal.petcare.gui.fragments.NewPet
import androidx.navigation.ui.setupWithNavController
import com.cuidadoanimal.petcare.gui.fragments.MapsFragment
import com.cuidadoanimal.petcare.gui.fragments.ArticlesFragment
import com.cuidadoanimal.petcare.gui.fragments.Vaccine
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :
    AppCompatActivity(),
    NewPet.NewPetListener,
    ArticlesFragment.OnFragmentInteractionListener,
    Vaccine.OnFragmentInteractionListener {

    override fun onInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private var doubleBackToExitPressedOnce = false

    private val auth = FirebaseAuth.getInstance()

    lateinit var viewModel: PetCareViewModel
    private var userID: Long = 0

    fun saveUser(displayName: String, email: String) {

        // Ingresar el usuario recientemente registrado, a la base de datos.
        var newUser = User()  // Crear nuevo usuario

        with(newUser) {
            // Asignar los valores del usuario actual
            this.name = displayName
            this.email = email
        }

        userID = /* Guardar ID del registro nuevo */
            viewModel.insert(newUser)
    }

    override fun insertPet(petName: String, petBreed: String, petSex: String) {

        val pet = Pet(
            name = petName,
            pet_breed = petBreed,
            owner = 1, /* TODO("ID del propietario quemado.") Acceder al ID del usuario actual en Room y mandarlo como FK*/
            sex = petSex
        )
        viewModel.insert(pet)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        var displayName: String? = intent?.getStringExtra("displayName")
        var email: String? = intent?.getStringExtra("email")

        if (displayName != null && email != null) saveUser(displayName, email)

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