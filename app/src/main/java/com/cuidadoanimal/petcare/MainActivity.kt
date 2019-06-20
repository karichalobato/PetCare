package com.cuidadoanimal.petcare

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cuidadoanimal.petcare.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val MY_REQUEST_CODE: Int = 7117 // Any number you want
    lateinit var providers : List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init
        providers = Arrays.asList<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(), //Email login
            //AuthUI.IdpConfig.FacebookBuilder().build(), // Facebook login
            //AuthUI.IdpConfig.GoogleBuilder().build(), // Google login
            AuthUI.IdpConfig.PhoneBuilder().build() // Phone login
        )

        showSignInOptions()

        //Event
        btn_sign_out.setOnClickListener {
            //SingOut
            AuthUI.getInstance().signOut(this@MainActivity)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled = false
                    showSignInOptions() //Si todo es correcto se mostraran las optiones de entrada.
                }
                .addOnFailureListener {
                        exception -> Toast.makeText(this@MainActivity,exception.message, Toast.LENGTH_SHORT).show()
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE)
        {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK)
            {
                val user = FirebaseAuth.getInstance().currentUser // Get current user
                Toast.makeText(this,""+user!!.email,Toast.LENGTH_SHORT).show()

                btn_sign_out.isEnabled = true
            }
            else{
                Toast.makeText(this,""+response!!.error!!.message,Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun showSignInOptions() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.MyTheme)
            .build(),MY_REQUEST_CODE)
    }
}
