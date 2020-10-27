package com.example.collegex

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.collegex.data.AppDatabase
import com.example.collegex.data.NoteDao
import com.example.collegex.data.SampleDataClass
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: NoteDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.noteDao()!!
    }


    @Test
    fun createNotes() {
        // Context of the app under test.
        dao.insertAll(SampleDataClass.getNote())
        val count = dao.getCount()

        assertEquals(count, SampleDataClass.getNote().size)
    }

    @After
    fun closeDb(){
        database.close()
    }
}