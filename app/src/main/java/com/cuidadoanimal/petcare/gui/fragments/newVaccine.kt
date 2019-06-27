package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.net.Uri
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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [newVaccine.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [newVaccine.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class newVaccine : Fragment() {

    private var listenerTool: NewPetListener? = null



    interface NewPetListener {
        fun insertVaccine(VaccineName: String, date: String)
    }

    private fun initSearchButton(container: View) =
        container.btncreatePet.setOnClickListener {

            if (VaccineName.text.isEmpty() || Date.text.isEmpty()) {
                Toast.makeText(this.context!!, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context!!, "Vacuna añadida exitosamente", Toast.LENGTH_SHORT).show()

                listenerTool?.insertVaccine(VaccineName.text.toString(), Date.text.toString())

                findNavController(this)
                    .navigate(R.id.action_newVaccine_dest_to_pet_dest)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_vaccine, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchButton(view)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NewPetListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz NewPetListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}
