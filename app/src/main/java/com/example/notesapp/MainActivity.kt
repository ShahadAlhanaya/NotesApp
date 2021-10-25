package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    lateinit var titleEditText: EditText
    lateinit var noteEditText: EditText
    lateinit var getTitleEditText: EditText
    lateinit var addButton: Button
    lateinit var getButton: Button
    lateinit var recyclerView: RecyclerView
    var notesList = arrayListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleEditText = findViewById(R.id.edt_title)
        noteEditText = findViewById(R.id.edt_note)
        getTitleEditText = findViewById(R.id.edt_getTitle)
        addButton = findViewById(R.id.btn_add)
        getButton = findViewById(R.id.btn_get)
        //initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RVAdapter(notesList)


        //initialize DB
        dbHelper = DBHelper(applicationContext)
        dbHelper.writableDatabase
        retrieveAllData()

        addButton.setOnClickListener {

            val title = titleEditText.text.toString()
            val note = noteEditText.text.toString()

            if (note.trim().isNotEmpty() && title.trim().isNotEmpty()) {

                val id = dbHelper.addNote(title, note)

                if (id >= 0) {
                    Toast.makeText(applicationContext, "added successfully!", Toast.LENGTH_SHORT)
                        .show()
                    retrieveAllData()
                    clearFields()
                    recyclerView.scrollToPosition(notesList.size - 1);


                } else {
                    Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }

            } else {
                Toast.makeText(applicationContext, "please enter your note", Toast.LENGTH_SHORT)
                    .show()

            }

        }

        getButton.setOnClickListener {
            val title = getTitleEditText.text.toString()

            if (title.trim().isNotEmpty()) {
                retrieveNote(title)
            } else {
                Toast.makeText(applicationContext, "please enter the title", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun retrieveAllData() {
        notesList.clear()
        notesList.addAll(dbHelper.retrieveAllNotes())
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun retrieveNote(title: String) {
        notesList.clear()
        notesList.addAll(dbHelper.retrieveNote(title.lowercase()))
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.allNotes -> {
                retrieveAllData()
                clearFields()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearFields() {
        getTitleEditText.text.clear()
        getTitleEditText.clearFocus()
        noteEditText.text.clear()
        titleEditText.text.clear()
        noteEditText.clearFocus()
        titleEditText.clearFocus()
    }

}