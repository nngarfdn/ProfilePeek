package com.apps.profilepeek.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apps.profilepeek.core.data.local.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao() : PersonDao
    companion object {
        const val NAME = "person.db"
    }
}