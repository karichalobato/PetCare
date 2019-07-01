package com.cuidadoanimal.petcare.gui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import com.cuidadoanimal.petcare.gui.adapters.FirestorePetAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_main.view.*


class HomeFragment : Fragment() {
    private lateinit var petsAdapter: FirestorePetAdapter

    private lateinit var info: PetCareViewModel

    private lateinit var petsCollection: CollectionReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        info = ViewModelProviders.of(this).get(PetCareViewModel::class.java)

        petsCollection = info.getAllPets()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        bind(view)
        return view
    }

    private fun bind(view: View) {
        /**
         * Agregar el adaptador y layoutManager al recyclerView de mascotas
         * */

        /** Consultar todos los elementos de la colección*/
        val query = petsCollection.orderBy(
            "pet_name",
            Query.Direction.ASCENDING
        )

        val options: FirestoreRecyclerOptions<Pet> =
            FirestoreRecyclerOptions
                .Builder<Pet>()
                .setQuery(
                    query,
                    Pet::class.java
                )
                .build()

        petsAdapter = FirestorePetAdapter(options)

        /*view.rv_pets.setHasFixedSize(true)*/
        view.rv_pets.adapter = this.petsAdapter
        view.rv_pets.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onStart() {
        super.onStart()
        petsAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        petsAdapter.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.bt_new_pet)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_new_pet, null)
        )
    }
}