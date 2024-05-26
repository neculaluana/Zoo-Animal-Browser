// AppDatabase.kt
package com.example.zooanimalbrowser.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zooanimalbrowser.data.models.AnimalDBModel
import com.example.zooanimalbrowser.data.models.ContinentDBModel

@Database(entities = [AnimalDBModel::class, ContinentDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}
