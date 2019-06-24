package com.cuidadoanimal.petcare.gui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import com.cuidadoanimal.petcare.gui.adapters.PetsAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Main : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewAdapter: PetsAdapter
    lateinit var viewModel: PetCareViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        bind(view)

        // Inflate the layout for this fragment
        return view
    }


    fun bind(view: View) {
        viewModel = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
        viewAdapter = PetsAdapter(ArrayList())

        view.rv_pets.adapter = this.viewAdapter
        view.rv_pets.layoutManager = LinearLayoutManager(this.context) as RecyclerView.LayoutManager?

        viewModel.allPets.observe(this, Observer {
            viewAdapter.updateList(it)
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<TextView>(R.id.butnew)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_new, null)
        )
    }


}
