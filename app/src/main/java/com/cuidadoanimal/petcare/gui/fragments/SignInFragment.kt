package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.gui.interfaces.OnDataPass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignInFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnDataPass? = null
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = auth.currentUser
        if (user != null) {

        }}

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_sign_in, container, false)
        }

        fun saveUser(user: FirebaseUser) {    // TODO mandarlo a llamar con los datos completos luego de la validación de auth
            listener?.saveUser(user)
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            if (context is OnDataPass) {
                listener = context
            } else {
                throw RuntimeException("Se necesita una implementación de  la interfaz OnDataPass")
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<TextView>(R.id.btn_signup)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_sing, null)
        )
    }

        override fun onDetach() {
            super.onDetach()
            listener = null
        }


    }
