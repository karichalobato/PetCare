package com.cuidadoanimal.petcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tag")
data class Tag (
    @ColumnInfo(name = "Name")
    val name: String
)

{
    @PrimaryKey(autoGenerate = true)
    var idTag:Int=0
}
