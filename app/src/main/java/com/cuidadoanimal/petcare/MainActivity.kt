package com.cuidadoanimal.petcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Toast
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.database.entities.Pet
import com.cuidadoanimal.petcare.viewModel.PetCareViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), newpet.NewPetListener {
    lateinit var viewModel:PetCareViewModel
    override fun insertpet(petname: String) {
        Log.d("holi","holi")
        val pet: Pet = Pet(name = petname,pet_photo = petname,pet_breed = petname,sex = petname,
            owner = petname,size = petname)

        viewModel.insertPet(pet)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}
