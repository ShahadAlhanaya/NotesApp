package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "notes.db"
        private const val TABLE_NOTES = "Notes"

        private const val KEY_ID = "ID"
        private const val KEY_TITLE = "Title"
        private const val KEY_NOTE = "Note"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NOTES ($KEY_ID INTEGER PRIMARY KEY ,$KEY_TITLE TEXT, $KEY_NOTE TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }


    fun addNote(title: String, note: String): Long{
        val sqLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, title)
        contentValues.put(KEY_NOTE, note)
        val success = sqLiteDatabase.insert(TABLE_NOTES,null, contentValues)
        sqLiteDatabase.close()
        return success
    }

    fun retrieveAllNotes(): ArrayList<Note>{
        val sqLiteDatabase = writableDatabase
        var list = arrayListOf<Note>()
        val cursor : Cursor = sqLiteDatabase.query(TABLE_NOTES, null,null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex(KEY_ID))
                val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                val note = cursor.getString(cursor.getColumnIndex(KEY_NOTE))
                list.add(Note(id.toLong(), title, note))
            } while (cursor.moveToNext());
        }
//        sqLiteDatabase.close()
        return list
    }

    fun retrieveNote(title: String): ArrayList<Note>{
        val sqLiteDatabase = writableDatabase
        var list = arrayListOf<Note>()
        val cursor : Cursor = sqLiteDatabase.query(TABLE_NOTES, null,"LOWER($KEY_TITLE)=?",
            arrayOf(title),null,null,null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex(KEY_ID))
                val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                val note = cursor.getString(cursor.getColumnIndex(KEY_NOTE))
                list.add(Note(id.toLong(), title, note))
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close()
        return list
    }

    fun updateNote(id :Long ,title: String, note: String): Int{
        val sqLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, title)
        contentValues.put(KEY_NOTE, note)
        val success = sqLiteDatabase.update(TABLE_NOTES,contentValues, "$KEY_ID =?", arrayOf(id.toString()))
        sqLiteDatabase.close()
        return success
    }

    fun deleteNote(id: Long): Int{
        val sqLiteDatabase = writableDatabase
        val success = sqLiteDatabase.delete(TABLE_NOTES,"$KEY_ID =?", arrayOf(id.toString()))
        sqLiteDatabase.close()
        return success
    }


     fun reset() {
        writableDatabase!!.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
    }
}