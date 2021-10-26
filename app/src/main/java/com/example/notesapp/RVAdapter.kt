package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_row.view.*

class RVAdapter(
    private val context: MainActivity,
    private val notesList: List<Note>
) :
    RecyclerView.Adapter<RVAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTextView: TextView = itemView.tv_noteCardnote
        val titleTextView: TextView = itemView.tv_noteCardTitle
        val editButton: ImageButton = itemView.imgBtn_noteCardEdit
        val deleteButton: ImageButton = itemView.imgBtn_noteCardDelete
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

        holder.editButton.setOnClickListener {
            context.showEditDialog(position)
        }
        holder.deleteButton.setOnClickListener {
            context.showDeleteDialog(position)
        }
    }


    override fun getItemCount() = notesList.size
}
