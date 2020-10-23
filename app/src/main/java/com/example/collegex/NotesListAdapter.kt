package com.example.collegex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collegex.data.NoteEntity
import com.example.collegex.databinding.ListItemBinding

class NotesListAdapter(private val notesList : List<NoteEntity>) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        val binding = ListItemBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val note = notesList[p1]
        with(p0.binding){
            txtNote.text = note.text
        }
    }

    override fun getItemCount() = notesList.size
}