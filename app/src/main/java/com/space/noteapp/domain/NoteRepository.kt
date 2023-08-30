package com.space.noteapp.domain

import com.space.noteapp.data.local.database.entitiy.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<NoteModel>>

    suspend fun getNoteById(id: Int): NoteModel

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(id: Int)
}