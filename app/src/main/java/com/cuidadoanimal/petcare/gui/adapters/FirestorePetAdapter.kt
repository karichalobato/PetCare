package com.cuidadoanimal.petcare.gui.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Pet
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.pet_list_item.view.*

class FirestorePetAdapter
internal constructor(options: FirestoreRecyclerOptions<Pet>) :
    FirestoreRecyclerAdapter<Pet, FirestorePetAdapter.PetViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PetViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.pet_list_item,
                    parent,
                    false
                )
        )


    override fun onBindViewHolder(
        viewHolder: PetViewHolder,
        p1: Int, pet: Pet
    ) {
        viewHolder.bind(pet)
    }

    inner class PetViewHolder
    internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal fun bind(pet: Pet) {
            itemView.tv_pet_name.text = pet.pet_name

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("petName", pet.pet_name)

                Log.d("F_PetAdapter", "PASSING ARG: ${bundle.getString("petName")}")

                Navigation.findNavController(itemView).navigate(
                    R.id.pet_dest, bundle
                )
            }
        }
    }
}