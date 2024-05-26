// ContinentDBModel.kt
package com.example.zooanimalbrowser.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "continents")
data class ContinentDBModel(
    @PrimaryKey val name: String
)
