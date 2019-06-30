package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "VaccineJOINPet",
    primaryKeys = ["idVaccine", "idPet"],
    foreignKeys = [
        ForeignKey(
            entity = Vaccine::class,
            parentColumns = ["idVaccine"],
            childColumns = ["idVaccine"]
        ),
        ForeignKey(
            entity = Pet::class,
            parentColumns = ["idPet"],
            childColumns = ["idPet"]
        )]
)
data class VaccineJOINPet(
    var idVaccine: Long,
    var idPet: Long,

    @ColumnInfo(name = "Date_Of_Application")
    val dateOfApplication: Long
)