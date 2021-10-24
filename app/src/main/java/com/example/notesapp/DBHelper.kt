package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, "notes.db", null, 1) {

    var sqlLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table notes (Note text)")
        }
    }

    fun saveNote(note: String): Long{
        val contentValues = ContentValues()
        contentValues.put("Note", note)
        return sqlLiteDatabase.insert("notes",null, contentValues)
    }

    //we're not gonna implement it yet
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}