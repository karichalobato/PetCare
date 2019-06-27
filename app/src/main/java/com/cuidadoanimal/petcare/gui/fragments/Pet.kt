package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.data.viewmodels.PetCareViewModel
import com.cuidadoanimal.petcare.gui.adapters.PetsAdapter
import com.cuidadoanimal.petcare.gui.adapters.VaccineAdapter
import kotlinx.android.synthetic.main.fragment_list_pet_item.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_newpet.*
import kotlinx.android.synthetic.main.fragment_newpet.view.*
import kotlinx.android.synthetic.main.fragment_newpet.view.PetBreed
import kotlinx.android.synthetic.main.fragment_newpet.view.PetName
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Pet.OnFragmentInteractionListener] interface
 * to handle onInteraction events.
 * Use the [Pet.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Pet : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewAdapter: VaccineAdapter
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
        val view = inflater.inflate(R.layout.fragment_pet, container, false)
        bind(view)

        // Inflate the layout for this fragment
        return view
    }
    fun bind(view: View) {
        viewModel = ViewModelProviders.of(this).get(PetCareViewModel::class.java)
        viewAdapter = VaccineAdapter(ArrayList())

        view.rv_pets.adapter = this.viewAdapter
        view.rv_pets.layoutManager = GridLayoutManager(this.context,3)

        viewModel.allVaccines.observe(this, Observer {
            viewAdapter.updateList(it)
        })

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.butnew)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_pet_dest_to_newVaccine_dest, null)
        )
    }
}




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an onInteraction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */




