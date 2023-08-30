package com.space.noteapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.noteapp.data.local.database.dao.NotesDao
import com.space.noteapp.data.local.database.entitiy.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}