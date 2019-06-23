package com.cuidadoanimal.petcare

import android.app.Activity
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
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class login : Fragment() {
    // TODO: Rename and change types of parameters

    private val MY_REQUEST_CODE: Int = 7117 // Any number you want
    lateinit var providers: List<AuthUI.IdpConfig>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //Init
        providers = Arrays.asList<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(), //Email login
            AuthUI.IdpConfig.FacebookBuilder().build(), // Facebook login
            AuthUI.IdpConfig.GoogleBuilder().build(), // Google login
            AuthUI.IdpConfig.PhoneBuilder().build() // Phone login
        )

        showSignInOptions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_login, container, false)

        v.btn_sign_out.setOnClickListener {
            //SingOut
            AuthUI.getInstance().signOut(this!!.context!!)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled = false
                    welcome.isEnabled = false
                    showSignInOptions() //Si todo es correcto se mostraran las optiones de entrada.
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this!!.context!!, exception.message, Toast.LENGTH_SHORT).show()
                }
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.welcome)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.welcome_action, null))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser // Get current user
                Toast.makeText(this!!.context!!, "" + user!!.email, Toast.LENGTH_SHORT).show()
                welcome.isEnabled = true
                btn_sign_out.isEnabled = true
            } else {
                Toast.makeText(this!!.context!!, "" + response!!.error!!.message, Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun showSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_cat_logo_login)
                .setTheme(R.style.MyTheme)
                .build(), MY_REQUEST_CODE
        )
    }
}
