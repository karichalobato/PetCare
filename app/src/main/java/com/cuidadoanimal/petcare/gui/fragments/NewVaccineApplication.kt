package com.cuidadoanimal.petcare.gui.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cuidadoanimal.petcare.R
import kotlinx.android.synthetic.main.fragment_new_vaccine_application.view.*
import java.util.*

private const val ARG_PET_NAME = "petName"
private const val ARG_VACCINE_NAME = "vaccineName"

class NewVaccineApplication : Fragment() {

    private var day = 1
    private var month = 1
    private var year = 1

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
        fun insertApplication(petName: String, vaccineName: String, year: String, month: String, day: String)
    }

    private fun initButtons(container: View) {

        container.bt_create_application.setOnClickListener {

            if (/*selectDate.text.isEmpty() || */this.year == 1) {
                Toast.makeText(this.context!!, "Ingresa la fecha de aplicación", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context!!, "Aplicación añadida exitosamente", Toast.LENGTH_SHORT).show()

                listenerTool?.insertApplication(
                    petName!!,
                    vaccineName!!,
                    this.year.toString(),
                    this.month.toString(),
                    this.day.toString()
                )

                val bundle = Bundle()
                bundle.putString("petName", petName)
                bundle.putString("vaccineName", vaccineName)

                findNavController(this)
                    .navigate(R.id.action_new_vaccine_application_dest_to_vaccine_details_dest, bundle)
            }
        }

        container.selectDate.setOnClickListener {

            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            val picker = DatePickerDialog(
                this.context!!,
                DatePickerDialog.OnDateSetListener { _, SelectedYear, monthOfYear, dayOfMonth ->

                    this.year = SelectedYear
                    this.month = monthOfYear + 1
                    this.day = dayOfMonth

                    container.selectDate.text =
                        getString(
                            R.string.display_date,
                            if (this.day.toString().length == 1) "0${this.day}" else this.day.toString(),
                            if (this.month.toString().length == 1) "0${this.month}" else this.month.toString(),
                            SelectedYear.toString()
                        )
                }, year, month, day
            )
            picker.show()
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

        initButtons(view)
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