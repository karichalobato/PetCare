package com.cuidadoanimal.petcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "Name")
    var name: String? = "N/A",
    @ColumnInfo(name = "Email")
    var email: String? = "N/A"
) {
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0
}