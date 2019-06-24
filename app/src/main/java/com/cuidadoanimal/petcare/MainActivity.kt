package com.cuidadoanimal.petcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.ViewModelProviders
import com.cuidadoanimal.petcare.database.entities.Pet
import com.cuidadoanimal.petcare.database.entities.User
import com.cuidadoanimal.petcare.viewModel.PetCareViewModel
import com.google.firebase.auth.FirebaseUser


class MainActivity :
    AppCompatActivity(),
    newpet.NewPetListener,
    Login.OnDataPass {

    lateinit var viewModel: PetCareViewModel
    private var userID: Long = 0

    override fun saveUser(user: FirebaseUser) {

        // Ingresar el usuario recientemente registrado, a la base de datos.
        var newUser = User()  // Crear nuevo usuario

        with(newUser) {
            // Asignar los valores del usuario actual
            name = user.displayName
            email = user.email
        }

        userID = /* Guardar ID del registro nuevo */
            viewModel.insert(newUser)


    }

    override fun insertPet(petName: String) {

        val pet = Pet(           // TODO("Solicitar datos de mascota desde UI.")
            name = petName,
            owner = 1 /* TODO("ID del propietario quemado.") Acceder al ID del usuario actual en Room y mandarlo como FK*/
        )

        viewModel.insert(pet)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
    }


}
