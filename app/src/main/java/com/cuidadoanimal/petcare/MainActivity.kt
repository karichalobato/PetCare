package com.cuidadoanimal.petcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.cuidadoanimal.petcare.database.entities.Pet
import com.cuidadoanimal.petcare.database.entities.User
import com.cuidadoanimal.petcare.viewModel.PetCareViewModel


class MainActivity : AppCompatActivity(), newpet.NewPetListener {
    lateinit var viewModel: PetCareViewModel


    override fun insertpet(petname: String) {
        Log.d("holi", petname)

        val owner: User = User() // recibir datos directo desde la autenticación
        viewModel.insert(owner)

        val pet: Pet = Pet(
            name = petname,
            pet_photo = petname,
            pet_breed = petname,
            sex = petname,
            owner = 1, /* Acceder al ID del usuario actual en Room y mandarlo como FK*/
            size = petname
        )
        Log.d("holi", pet.name)
        viewModel.insert(pet)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
    }


}
