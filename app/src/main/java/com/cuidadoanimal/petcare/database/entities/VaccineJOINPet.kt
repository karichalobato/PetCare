package com.cuidadoanimal.petcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.*

@Entity(
    tableName = "VaccineJOINPet",
    primaryKeys = ["vacunaID", "mascotaID"],
    foreignKeys = [
        ForeignKey(
            entity = Vaccine::class,
            parentColumns = ["idVaccine"],
            childColumns = ["vacunaID"]
        ),
        ForeignKey(
            entity = Pet::class,
            parentColumns = ["idPet"],
            childColumns = ["mascotaID"]
        )]
)
data class VaccineJOINPet(
    var vacunaID: Long, var mascotaID: Long,
    @ColumnInfo(name = "Date_Of_Application")
    val dateOfApplication: Long
)