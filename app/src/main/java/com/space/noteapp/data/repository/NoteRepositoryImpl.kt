package com.space.noteapp.data.repository

import com.space.noteapp.data.local.database.dao.NotesDao
import com.space.noteapp.data.local.database.entitiy.NoteEntity
import com.space.noteapp.domain.NoteModel
import com.space.noteapp.domain.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val notesDao: NotesDao
) : NoteRepository {
    override fun getNotes(): Flow<List<NoteModel>> {
        return notesDao.getNotesFlow().map {
            it.map { item ->
                NoteModel(item.id, item.title, item.description)
            }
        }
    }

    override suspend fun getNoteById(id: Int): NoteModel {
        return notesDao.selectById(id).firstNotNullOf {
            NoteModel(it.id, it.title, it.description)
        }
    }

    override suspend fun insertNote(note: NoteEntity) {
        notesDao.insertNote(note)
    }

    override suspend fun deleteNote(id: Int) {
        notesDao.deleteNote(id)
    }
}