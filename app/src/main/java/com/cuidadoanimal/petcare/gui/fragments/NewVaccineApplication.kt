package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cuidadoanimal.petcare.R
import kotlinx.android.synthetic.main.fragment_new_vaccine.*
import kotlinx.android.synthetic.main.fragment_new_vaccine_application.view.*

private const val ARG_PET_NAME = "petName"
private const val ARG_VACCINE_NAME = "vaccineName"

class NewVaccineApplication : Fragment() {

    private var petName: String? = null
    private var vaccineName: String? = null

    private var listenerTool: NewVaccineApplicationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            petName = it.getString(ARG_PET_NAME)
            vaccineName = it.getString(ARG_VACCINE_NAME)
        }
    }

    interface NewVaccineApplicationListener {
        fun insertApplication(petName: String, vaccineName: String, date: String)
    }

    private fun initSearchButton(container: View) =
        container.bt_create_application.setOnClickListener {

            if (Date.text.isEmpty()) {
                Toast.makeText(this.context!!, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context!!, "Aplicación añadida exitosamente", Toast.LENGTH_SHORT).show()

                listenerTool?.insertApplication(petName!!, vaccineName!!, Date.text.toString())

                val bundle = Bundle()
                bundle.putString("petName", petName)
                bundle.putString("vaccineName", vaccineName)

                findNavController(this)
                    .navigate(R.id.action_new_vaccine_application_dest_to_vaccine_details_dest, bundle)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_vaccine_application, container, false)
        bind(view)
        return view
    }

    private fun bind(view: View) {
        view.tv_aplication_for_vaccine.text = getString(R.string.application_for_vaccine, vaccineName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchButton(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewVaccineApplicationListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz NewVaccineApplicationListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}