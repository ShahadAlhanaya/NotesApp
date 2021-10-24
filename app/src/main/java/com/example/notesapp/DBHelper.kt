package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "notes.db"
        private const val TABLE_NOTES = "Notes"

        private const val KEY_ID = "ID"
        private const val KEY_NOTE = "Note"
    }

    var sqLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NOTES ($KEY_ID INTEGER PRIMARY KEY ,$KEY_NOTE TEXT)")
    }

    fun addNote(note: String): Long{
        val contentValues = ContentValues()
        contentValues.put(KEY_NOTE, note)
        val success = sqLiteDatabase.insert(TABLE_NOTES,null, contentValues)
        sqLiteDatabase.close()
        return success
    }

    //we're not gonna implement it yet
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}