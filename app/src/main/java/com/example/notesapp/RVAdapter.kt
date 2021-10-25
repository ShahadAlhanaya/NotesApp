package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_row.view.*

class RVAdapter(private val notesList: List<Note>) :
    RecyclerView.Adapter<RVAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTextView: TextView = itemView.tv_noteCardnote
        val titleTextView: TextView = itemView.tv_noteCardTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_row,
            parent,
            false
        )
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.noteTextView.text = notesList[position].note
        holder.titleTextView.text = notesList[position].title
    }

    override fun getItemCount() = notesList.size
}
