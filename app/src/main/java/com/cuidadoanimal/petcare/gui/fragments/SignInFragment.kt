package com.cuidadoanimal.petcare.gui.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.cuidadoanimal.petcare.R
import com.cuidadoanimal.petcare.gui.interfaces.OnDataPass
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_sign_in.*
import com.cuidadoanimal.petcare.gui.activities.MainActivity
import android.content.Intent
import android.R
import android.R.attr.password
import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.firebase.auth.AuthResult
import androidx.annotation.NonNull
import android.R
import com.google.android.gms.tasks.Task


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
    ).apply {
        btn_login.setOnClickListener {
            if (TextUtils.isEmpty(email.text)) {
                Toast.makeText(this.context!!, "Enter email address!", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password.text)) {
                Toast.makeText(this.context!!, "Enter password!", Toast.LENGTH_SHORT).show()
            }

            progressBar.visibility = View.VISIBLE

            /*auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignInFragment, object : OnCompleteListener<AuthResult>() {
                    fun onComplete(task: Task<AuthResult>) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.visibility = View.GONE
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                password.setError(getString(R.string.minimum_password))
                            } else {
                                Toast.makeText(this.context!!, getString(R.string.auth_failed), Toast.LENGTH_LONG)
                                    .show()
                            }
                        } else {
                            val intent = Intent(this@SignInFragment, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                })
*/
        }

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
