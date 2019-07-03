package com.cuidadoanimal.petcare.gui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.VaccineApplication
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import com.cuidadoanimal.petcare.gui.adapters.VaccineApplicationAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_vaccine.view.*

private const val ARG_PET_NAME = "petName"
private const val ARG_VACCINE_NAME = "vaccineName"

class VaccineFragment : Fragment() {
    private var petName: String? = null
    private var vaccineName: String? = null

    private lateinit var info: PetCareViewModel

    private lateinit var vaccineApplicationCollection: CollectionReference

    private lateinit var vaccineApplicationAdapter: VaccineApplicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            petName = it.getString(ARG_PET_NAME)

            vaccineName = it.getString(ARG_VACCINE_NAME)
        }

        info = ViewModelProviders.of(this).get(PetCareViewModel::class.java)

        vaccineApplicationCollection = info.getAllVaccineApplications(petName!!, vaccineName!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vaccine, container, false)
        bind(view)
        return view
    }

    private fun bind(view: View) {
        /**
         * Agregar el adaptador y layoutManager al recyclerView de vacunas
         * */

        view.tv_vaccine_name.text = vaccineName

        val query = vaccineApplicationCollection.orderBy(
            "application_date",
            Query.Direction.DESCENDING
        )

        val options: FirestoreRecyclerOptions<VaccineApplication> =
            FirestoreRecyclerOptions
                .Builder<VaccineApplication>()
                .setQuery(
                    query,
                    VaccineApplication::class.java
                )
                .build()

        vaccineApplicationAdapter = VaccineApplicationAdapter(options)

        /*view.rv_vaccines.setHasFixedSize(true)*/
        view.rv_vaccine_applications.adapter = this.vaccineApplicationAdapter
        view.rv_vaccine_applications.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onStart() {
        super.onStart()
        vaccineApplicationAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        vaccineApplicationAdapter.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        bundle.putString("petName", petName)
        bundle.putString("vaccineName", vaccineName)

        view.findViewById<Button>(R.id.bt_new_vaccine_application)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.new_vaccine_application_dest, bundle)
        )

    }
}