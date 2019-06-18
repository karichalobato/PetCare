package com.cuidadoanimal.petcare.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuidadoanimal.petcare.R
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }
}
