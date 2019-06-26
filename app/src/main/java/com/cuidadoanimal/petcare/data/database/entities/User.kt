package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "User",
        indices = [Index(value = ["Email"],
                unique = true)])
data class User(
        @ColumnInfo(name = "Name")
        var name: String? = "N/A",
        @ColumnInfo(name = "Email")
        var email: String? = "N/A"
) {
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0
}