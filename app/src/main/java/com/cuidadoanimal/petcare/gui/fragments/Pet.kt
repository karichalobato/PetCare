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
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.cuidadoanimal.petcare.R
import kotlinx.android.synthetic.main.fragment_list_pet_item.view.*
import kotlinx.android.synthetic.main.fragment_newpet.*
import kotlinx.android.synthetic.main.fragment_newpet.view.*
import kotlinx.android.synthetic.main.fragment_newpet.view.PetBreed
import kotlinx.android.synthetic.main.fragment_newpet.view.PetName


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.btnvacine)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.vacine_action, null)
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




