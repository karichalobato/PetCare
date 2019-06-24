package com.cuidadoanimal.petcare.gui.interfaces

import com.google.firebase.auth.FirebaseUser

interface OnDataPass {
    fun saveUser(user: FirebaseUser)
}