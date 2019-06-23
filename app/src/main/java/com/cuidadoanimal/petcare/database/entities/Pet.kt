package com.cuidadoanimal.petcare.database.entities

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

    @ColumnInfo(name = "Pet_Photo")
    val pet_photo: String,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Owner")
    val owner: Int,
    @ColumnInfo(name = "Sex")
    val sex: String,
    @ColumnInfo(name = "Pet_Breed")
    val pet_breed: String,
    @ColumnInfo(name = "Size")
    val size: String
) {
    @PrimaryKey(autoGenerate = true)
    var idPet: Int = 0
}
