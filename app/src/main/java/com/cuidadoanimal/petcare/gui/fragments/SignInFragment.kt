package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.cuidadoanimal.petcare.gui.interfaces.OnDataPass
import com.google.firebase.auth.FirebaseAuth
import com.cuidadoanimal.petcare.R
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_sign_in.*

import android.app.Activity
import android.util.Log


class SignInFragment : Fragment() {
    private var listener: OnDataPass? = null
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = auth.currentUser
        if (user != null) { // si ya hay una sesión iniciada
            // abrir el fragmento principal
            Navigation.createNavigateOnClickListener(R.id.action_sing, null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_sign_in,
        container, false
    )

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

        btn_login.setOnClickListener {
            if (email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this.context!!, "Enter credentials", Toast.LENGTH_SHORT).show()
            } else {

                progressBar.visibility = View.VISIBLE

                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(context as Activity) { task ->

                        progressBar.visibility = View.INVISIBLE
                        if (task.isSuccessful) {

                            val user = auth.currentUser // Get current user

                            saveUser(user!!)

                            Navigation.createNavigateOnClickListener(R.id.action_sing, null)
                        } else {
                            // TODO("Error de autenticación") Mostrar mensaje de error al usuario
                            Toast.makeText(this.context!!, "Error de autenticación", Toast.LENGTH_LONG).show()
                            Log.d("LOG ME", "ERROR = ${task.exception}")
                        }
                    }
            }
        }

        btn_signup.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_sing, null)
        )
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
