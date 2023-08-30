package com.space.noteapp.domain.usecase

import com.space.noteapp.data.local.database.entitiy.NoteEntity
import com.space.noteapp.domain.NoteRepository

class InsertNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun invoke(note: NoteEntity) = noteRepository.insertNote(note)
}