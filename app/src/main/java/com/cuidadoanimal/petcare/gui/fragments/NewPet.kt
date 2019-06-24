package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.cuidadoanimal.petcare.R


import kotlinx.android.synthetic.main.fragment_newpet.*
import kotlinx.android.synthetic.main.fragment_newpet.view.*


class NewPet : Fragment() {

    var listenerTool: NewPetListener? = null

    interface NewPetListener {
        fun insertPet(petName: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initSearchButton(container: View) =
        container.btncreatePet.setOnClickListener {
            listenerTool?.insertPet(PetName.text.toString())
        }

    //fun initSearchButton(container:View) = container.btncreateMatch.setOnClickListener {
    //   listenerTool?.insertMatch{val match: Match=Match(team1 = container.team1.text.toString(),
    //        team2 = container.team2.text.toString(),date = "dd/mm/yy",commit = "nice",id = 0)}
    //  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_newpet, container, false)
        view.findViewById<Button>(R.id.btncreatePet)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_newpet_to_main, null)
        )
        initSearchButton(view)
        // Inflate the layout for this fragment
        return view
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
