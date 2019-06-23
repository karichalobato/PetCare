package com.cuidadoanimal.petcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "Profile_Picture")
    val profile_picture: String = "N/A",
    @ColumnInfo(name = "Name")
    val name: String = "N/A",
    @ColumnInfo(name = "Username")
    val username: String = "N/A",
    @ColumnInfo(name = "Password")
    val password: String = "N/A",
    @ColumnInfo(name = "Email")
    val email: String = "N/A"
) {
    @PrimaryKey(autoGenerate = true)
    var idUser: Int = 0
}