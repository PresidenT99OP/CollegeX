package com.example.collegex.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import java.security.AccessControlContext
import java.util.concurrent.locks.Lock

@Database(entities = [NoteEntity::class] , version = 1 , exportSchema = false)
@TypeConverters(DateConvertor ::class)

abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao?

    companion object {
        private var INSTANCE : AppDatabase? = null



        @InternalCoroutinesApi
        fun getInstance(context: Context ):AppDatabase? {
            if (INSTANCE == null) {
                synchronized(Any()) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "NoteDatabase.db").build()
                }

            }
            return INSTANCE
        }
    }

}