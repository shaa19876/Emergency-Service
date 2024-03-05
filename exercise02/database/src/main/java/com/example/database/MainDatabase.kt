package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.dao.DatabaseDAO
import com.example.database.models.IncidentDBO
import com.example.database.models.ZoneDBO

@Database(
  entities = [ZoneDBO::class, IncidentDBO::class],
  version = 1
)
abstract class MainDatabase : RoomDatabase() {
  abstract fun dao(): DatabaseDAO
}

fun mainDatabase(applicationContext: Context): MainDatabase {
  return Room.databaseBuilder(
    checkNotNull(applicationContext.applicationContext),
    MainDatabase::class.java,
    "main-db"
  ).build()
}