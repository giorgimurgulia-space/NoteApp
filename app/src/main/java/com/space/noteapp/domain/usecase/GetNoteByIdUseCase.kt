package com.space.noteapp.domain.usecase

import com.space.noteapp.domain.NoteRepository


class GetNoteByIdUseCase(private val noteRepository: NoteRepository) {
    suspend fun invoke(noteId: Int) = noteRepository.getNoteById(noteId)
}