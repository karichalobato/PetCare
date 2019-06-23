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

class Login : Fragment() {
    private val MY_REQUEST_CODE: Int = 7117 // Any number you want
    lateinit var providers: List<AuthUI.IdpConfig>
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (auth.currentUser == null) {

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
                    Toast.makeText(this!!.context!!, exception.message, Toast.LENGTH_SHORT).show()
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
                .setLogo(R.drawable.cat)
                .setTheme(R.style.MyTheme)
                .setIsSmartLockEnabled(false)
                .build(), MY_REQUEST_CODE
        )
    }
}
