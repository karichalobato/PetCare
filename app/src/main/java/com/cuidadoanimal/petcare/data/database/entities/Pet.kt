package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Pet", foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["idUser"], childColumns = ["Owner"]
    )]
)
data class Pet(

    @ColumnInfo(name = "Name")
    val name: String? = "N/A",
    @ColumnInfo(name = "Owner")
    val owner: Long = 1,
    @ColumnInfo(name = "Sex")
    val sex: String? = "N/A",
    @ColumnInfo(name = "Pet_Breed")
    val pet_breed: String? = "N/A",
    @ColumnInfo(name = "Size")
    val size: String? = "N/A"
) {
    @PrimaryKey(autoGenerate = true)
    var idPet: Long = 0
}