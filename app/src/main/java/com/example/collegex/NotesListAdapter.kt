package com.example.collegex

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegex.data.NoteEntity

class NotesListAdapter(private val notesList : List<NoteEntity>) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):
        RecyclerView.Adapter(itemView){

    }
    {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}