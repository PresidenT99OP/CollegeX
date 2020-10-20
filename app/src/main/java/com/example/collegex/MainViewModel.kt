package com.example.collegex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.collegex.data.NoteEntity
import com.example.collegex.data.SampleDataClass

class MainViewModel : ViewModel() {

    lateinit var notesList : MutableLiveData<List<NoteEntity>>

    init {

        notesList.value = SampleDataClass.getNote()

    }




}