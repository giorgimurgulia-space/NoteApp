package com.space.noteapp.data.local.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

private fun provideFavouriteMovieDatabase(context: Context): NotesDatabase {
    return Room.databaseBuilder(context, NotesDatabase::class.java, "notes")
        .fallbackToDestructiveMigration().build()
}

private fun provideFavouriteMovieDao(db: NotesDatabase) = db.notesDao()

val dataBaseModule = module {
    single { provideFavouriteMovieDatabase(get()) }
    single { provideFavouriteMovieDao(get()) }
}