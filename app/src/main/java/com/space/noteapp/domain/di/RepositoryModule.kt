package com.space.noteapp.domain.di

import com.space.noteapp.data.repository.NoteRepositoryImpl
import com.space.noteapp.domain.NoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NoteRepository> {
        NoteRepositoryImpl(
            notesDao = get()
        )
    }
}