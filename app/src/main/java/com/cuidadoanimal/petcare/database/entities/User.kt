package com.cuidadoanimal.petcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @ColumnInfo(name = "Profile_Picture")
    val profile_picture: String,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Username")
    val username: String,
    @ColumnInfo(name = "Password")
    val password: String,
    @ColumnInfo(name = "Email")
    val email: String
)
{
    @PrimaryKey(autoGenerate = true)
    var idUser: Int = 0
}