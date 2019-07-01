package com.cuidadoanimal.petcare.gui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.VaccineApplication
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.vaccine_application_list_item.view.*

class FirestoreApplicationAdapter
internal constructor(options: FirestoreRecyclerOptions<VaccineApplication>) :
        FirestoreRecyclerAdapter<VaccineApplication, FirestoreApplicationAdapter.ApplicationViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ApplicationViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(
                                    R.layout.vaccine_application_list_item,
                                    parent,
                                    false
                            )
            )

    override fun onBindViewHolder(
        viewHolder: ApplicationViewHolder,
        p1: Int, vaccineApplication: VaccineApplication
    ) {
        viewHolder.bind(vaccineApplication)
    }

    inner class ApplicationViewHolder
    internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal fun bind(vaccineApplication: VaccineApplication) {
            var displayDate = "${vaccineApplication.application_day}/${vaccineApplication.application_month}/${vaccineApplication.application_year}"
            itemView.tv_application_date.text = displayDate
        }
    }
}