package com.cuidadoanimal.petcare.gui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.vaccine_list_item.view.*

class FirestoreVaccineAdapter
internal constructor(options: FirestoreRecyclerOptions<Vaccine>) :
    FirestoreRecyclerAdapter<Vaccine, FirestoreVaccineAdapter.VaccineViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VaccineViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.vaccine_list_item,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(
        viewHolder: VaccineViewHolder,
        p1: Int, vaccine: Vaccine
    ) {
        viewHolder.bind(vaccine)
    }

    inner class VaccineViewHolder
    internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal fun bind(vaccine: Vaccine) {
            itemView.tv_vaccine_name.text = vaccine.vaccine_name

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("vaccineName", vaccine.vaccine_name)

                /*Navigation.findNavController(itemView).navigate(
                    R.id.pet_dest, bundle
                ) // TODO(VACCINE DETAILS NO EXISTE)*/
            }
        }
    }
}