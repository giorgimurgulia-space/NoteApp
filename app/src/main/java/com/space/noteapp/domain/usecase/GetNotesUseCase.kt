package com.space.noteapp.domain.usecase


import com.space.noteapp.domain.NoteModel
import com.space.noteapp.domain.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    fun invoke(): Flow<List<NoteModel>> = noteRepository.getNotes()
}