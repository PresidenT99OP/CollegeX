package com.example.collegex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collegex.data.AppDatabase
import com.example.collegex.data.NoteEntity
import com.example.collegex.data.SampleDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app : Application): AndroidViewModel(app) {

    @InternalCoroutinesApi
    private val database = AppDatabase.getInstance(app)

    @InternalCoroutinesApi
    val  notesList = database?.noteDao()?.getAll()

    @InternalCoroutinesApi
    fun addSampleData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val sampleNotes = SampleDataClass.getNote()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }

    @InternalCoroutinesApi
    fun deleteNotes(selectedNotes: List<NoteEntity>) {

        viewModelScope.launch {
            withContext(Dispatchers.IO){

                database?.noteDao()?.deleteNotes(selectedNotes)

            }
        }

    }

    @InternalCoroutinesApi
    fun deleteAllNote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){

                database?.noteDao()?.deleteAllNotes()

            }
        }
    }


}