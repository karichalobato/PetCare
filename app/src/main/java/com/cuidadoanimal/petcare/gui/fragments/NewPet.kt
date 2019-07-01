package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cuidadoanimal.petcare.R


import kotlinx.android.synthetic.main.fragment_newpet.*
import kotlinx.android.synthetic.main.fragment_newpet.view.*


class NewPet : Fragment(), View.OnClickListener {

    private var listenerTool: NewPetListener? = null

    private var sex = "F"

    interface NewPetListener {
        fun insertPet(petName: String, petBreed: String, petSex: String)
    }

    private fun initSearchButton(container: View) =
            container.btncreateVaccine.setOnClickListener {

                if (PetName.text.isEmpty() || PetBreed.text.isEmpty()) {
                    Toast.makeText(this.context!!, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this.context!!, "Mascota añadida exitosamente", Toast.LENGTH_SHORT).show()

                    listenerTool?.insertPet(PetName.text.toString(), PetBreed.text.toString(), sex)

                    findNavController(this)
                            .navigate(R.id.action_newpet_to_main)
                }
            }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_newpet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchButton(view)

        view.findViewById<Button>(R.id.rb_sex_female)?.setOnClickListener(this)
        view.findViewById<Button>(R.id.rb_sex_male)?.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.rb_sex_female ->
                    if (checked) {
                        this.sex = "F"
                    }
                R.id.rb_sex_male ->
                    if (checked) {
                        this.sex = "M"
                    }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewPetListener) {
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
