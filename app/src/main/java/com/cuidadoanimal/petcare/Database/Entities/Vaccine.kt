package com.cuidadoanimal.petcare.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Vaccine")
data class Vaccine (

    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Next_Application")
    val next_application: Date,
    @ColumnInfo(name = "Contraindications")
    val contraindications: String
)

{
    @PrimaryKey(autoGenerate = true)
    var idVaccine: Int = 0
}