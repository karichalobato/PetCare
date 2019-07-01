package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cuidadoanimal.petcare.R
import kotlinx.android.synthetic.main.fragment_new_vaccine.*
import kotlinx.android.synthetic.main.fragment_new_vaccine.view.*

private const val ARG_PET_NAME = "petName"

class NewVaccine : Fragment() {

    private var petName: String? = null

    private var listenerTool: NewVaccineListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            petName = it.getString(ARG_PET_NAME)
        }
    }

    interface NewVaccineListener {
        fun insertVaccine(petName: String, vaccineName: String, date: String)
    }

    private fun initSearchButton(container: View) =
        container.btncreateVaccine.setOnClickListener {

            if (VaccineName.text.isEmpty() || Date.text.isEmpty()) {
                Toast.makeText(this.context!!, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context!!, "Vacuna añadida exitosamente", Toast.LENGTH_SHORT).show()

                listenerTool?.insertVaccine(petName!!, VaccineName.text.toString(), Date.text.toString())

                val bundle = Bundle()
                bundle.putString("petName", petName)

                findNavController(this)
                    .navigate(R.id.action_newVaccine_dest_to_pet_dest, bundle)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_vaccine, container, false)
        bind(view)
        return view
    }

    private fun bind(view: View) {
        view.tv_vaccine_for_pet_name.text = getString(R.string.vaccine_for_pet, petName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchButton(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewVaccineListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz NewVaccineListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}
