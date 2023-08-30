package com.space.noteapp.domain.usecase

import com.space.noteapp.domain.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun invoke(id: Int) = noteRepository.deleteNote(id)
}