package com.cuidadoanimal.petcare.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tag")
data class Tag(
    @ColumnInfo(name = "Name")
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var idTag: Long = 0
}
