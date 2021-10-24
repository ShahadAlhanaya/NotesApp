package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener {
            var note = edt_note.text.toString()
            if (note.trim().isNotEmpty()) {
                var dbHelper = DBHelper(applicationContext)
                if (dbHelper.saveNote(note) > 0) {
                    Toast.makeText(applicationContext, "added successfully!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "please enter a note", Toast.LENGTH_SHORT).show()

            }

        }


    }
}