package com.cuidadoanimal.petcare

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class Login : Fragment() {
    private val MY_REQUEST_CODE: Int = 7117 // Any number you want
    lateinit var providers: List<AuthUI.IdpConfig>
    private val auth = FirebaseAuth.getInstance()

    private var listenerTool: OnDataPass? = null

    interface OnDataPass {
        fun saveUser(user: FirebaseUser)
    }

    fun saveUser(user: FirebaseUser) {    // mandarlo a llamar con los datos completos luego de la validación de auth
        listenerTool?.saveUser(user)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataPass) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz OnDataPass")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = auth.currentUser
        if (user == null) {

            providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.FacebookBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build()
            )
            showSignInOptions()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_login,
        container,
        false
    ).apply {
        btn_sign_out.setOnClickListener {
            //SignOut
            AuthUI.getInstance().signOut(this!!.context!!)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled = false
                    welcome.isEnabled = false
                    showSignInOptions()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this.context!!, exception.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.welcome)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.welcome_action, null)
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val user = auth.currentUser // Get current user
                saveUser(user!!) // TODO("Usuario no registrado en base de datos local") Guardar al usuario y mandar el ID como parámetro entre destinos en NavEditor
                Toast.makeText(this.context!!, "" + user.email, Toast.LENGTH_SHORT).show()
                welcome.isEnabled = true
                btn_sign_out.isEnabled = true
            } else {
                Toast.makeText(this.context!!, "" + response!!.error!!.message, Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun showSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)  // TODO("Providers no inicializado con persistencia de sesión.") App crashea al querer cerrar sesión porque no se ejecutó instrucción que inicializa luego del registro, debido a la persistencia.
                .setLogo(R.drawable.cat)
                .setTheme(R.style.MyTheme)
                .setIsSmartLockEnabled(false)
                .build(), MY_REQUEST_CODE
        )
    }
}
