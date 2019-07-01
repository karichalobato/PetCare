package com.cuidadoanimal.petcare.gui.fragments

//import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.database.entities.Vaccine
import com.cuidadoanimal.petcare.gui.adapters.FirestoreVaccineAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_pet.view.*

private const val ARG_PET_NAME = "petName"

class PetFragment : Fragment() {
    private var petName: String? = null

    private val auth = FirebaseAuth.getInstance()

    private val myDB = FirebaseFirestore.getInstance()

    private lateinit var vaccinesCollection: CollectionReference

    private lateinit var vaccineAdapter: FirestoreVaccineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            petName = it.getString(ARG_PET_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet, container, false)
        bind(view)
        return view
    }

    private fun bind(view: View) {
        /**
         * Agregar el adaptador y layoutManager al recyclerView de vacunas
         * */

        view.tv_pet_name.text = petName

        vaccinesCollection = myDB
            .collection("users")
            .document(auth.currentUser?.email.toString())
            .collection("pets")
            .document(view.tv_pet_name.text.toString())
            .collection("vaccines")

        val query = vaccinesCollection.orderBy(
            "vaccine_name",
            Query.Direction.ASCENDING
        )

        val options: FirestoreRecyclerOptions<Vaccine> =
            FirestoreRecyclerOptions
                .Builder<Vaccine>()
                .setQuery(
                    query,
                    Vaccine::class.java
                )
                .build()

        vaccineAdapter = FirestoreVaccineAdapter(options)

        /*view.rv_vaccines.setHasFixedSize(true)*/
        view.rv_vaccines.adapter = this.vaccineAdapter
        view.rv_vaccines.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onStart() {
        super.onStart()
        vaccineAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        vaccineAdapter.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        bundle.putString("petName", petName)

        view.findViewById<TextView>(R.id.bt_new_vaccine)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_pet_dest_to_newVaccine_dest, bundle)
        )
    }
}