package com.cuidadoanimal.petcare.gui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Application
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.vaccine_application_list_item.view.*

class FirestoreApplicationAdapter
internal constructor(options: FirestoreRecyclerOptions<Application>) :
        FirestoreRecyclerAdapter<Application, FirestoreApplicationAdapter.ApplicationViewHolder>(options) {

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
            p1: Int, application: Application
    ) {
        viewHolder.bind(application)
    }

    inner class ApplicationViewHolder
    internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal fun bind(application: Application) {
            var displayDate = "${application.application_day}/${application.application_month}/${application.application_year}"
            itemView.tv_application_date.text = displayDate
        }
    }
}