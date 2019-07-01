package com.cuidadoanimal.petcare.gui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cuidadoanimal.petcare.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class AuthenticationActivity : AppCompatActivity() {

    private val MY_REQUEST_CODE: Int = 7117 // Any number you want
    private lateinit var providers: List<AuthUI.IdpConfig>
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = auth.currentUser
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {

            setContentView(R.layout.activity_authentication)

            providers = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.FacebookBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.PhoneBuilder().build()
            )
            showSignInOptions()

/*
        btn_sign_out.setOnClickListener {
            //SingOut
            AuthUI.getInstance().signOut(this@AuthenticationActivity)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled = false
                    showSignInOptions() //Si todo es correcto se mostraran las optiones de entrada.
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this@AuthenticationActivity, exception.message, Toast.LENGTH_SHORT).show()
                }
        }
*/
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {

                val user = FirebaseAuth.getInstance().currentUser // Get current firebaseUser
                Toast.makeText(this, "Welcome, ${user!!.displayName}!", Toast.LENGTH_SHORT).show()

                val intent = Intent(
                    this,
                    MainActivity::class.java
                )

                intent.putExtra("email", user.email)
                intent.putExtra("displayName", user.displayName)

                startActivity(intent)
                finish()

//                btn_sign_out.isEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Fail " + response!!.error!!.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun showSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .setLogo(R.drawable.cat)
                .setIsSmartLockEnabled(false)
                .build(), MY_REQUEST_CODE
        )
    }
}