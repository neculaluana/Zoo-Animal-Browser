package com.example.zooanimalbrowser

import android.app.Application
import androidx.room.Room
import com.example.zooanimalbrowser.data.AppDatabase

class ZooApplication : Application() {
    lateinit var database: AppDatabase
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "zoo-database"
        ).build()
    }
}
