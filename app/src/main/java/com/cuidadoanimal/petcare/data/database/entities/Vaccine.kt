package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vaccine")
data class Vaccine(

        @ColumnInfo(name = "Name")
        val name: String,
        @ColumnInfo(name = "Next_Application")
        val next_application: String,
        @ColumnInfo(name = "Contraindications")
        val contraindications: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    var idVaccine: Long = 0
}