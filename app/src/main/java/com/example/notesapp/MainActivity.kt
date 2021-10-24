package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var noteEditText: EditText
    lateinit var addButton: Button
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteEditText = findViewById(R.id.edt_note)
        addButton = findViewById(R.id.btn_add)
        recyclerView = findViewById(R.id.recyclerView)

        var dbHelper = DBHelper(applicationContext)

        addButton.setOnClickListener {
            val note = noteEditText.text.toString()
            if (note.trim().isNotEmpty()) {
                if (dbHelper.addNote(note) > 0) {
                    Toast.makeText(applicationContext, "added successfully!", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "please enter your note", Toast.LENGTH_SHORT).show()

            }

        }


    }
}