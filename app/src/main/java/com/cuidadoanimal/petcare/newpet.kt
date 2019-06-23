package com.cuidadoanimal.petcare

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation


import kotlinx.android.synthetic.main.fragment_newpet.*
import kotlinx.android.synthetic.main.fragment_newpet.view.*
import kotlinx.android.synthetic.main.fragment_newpet.view.PetName




class newpet : Fragment() {


    var listenerTool :  NewPetListener? = null


    interface NewPetListener{
        fun insertpet(petname: String)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    fun initSearchButton(container:View) = container.btncreatePet.setOnClickListener {
        Log.d("holi","quepex")
        listenerTool?.insertpet(PetName.text.toString())
    }

    //fun initSearchButton(container:View) = container.btncreateMatch.setOnClickListener {
    //   listenerTool?.insertMatch{val match: Match=Match(team1 = container.team1.text.toString(),
    //        team2 = container.team2.text.toString(),date = "dd/mm/yy",commit = "nice",id = 0)}
    //  }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_list_pet_item, container, false)
        view.findViewById<Button>(R.id.btncreatePet)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_newpet_to_main, null))
        initSearchButton(view)
        // Inflate the layout for this fragment
        return view
    }





    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }



}
