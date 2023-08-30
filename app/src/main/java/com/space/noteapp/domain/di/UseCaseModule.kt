package com.space.noteapp.domain.di


import com.space.noteapp.domain.usecase.DeleteNoteUseCase
import com.space.noteapp.domain.usecase.GetNoteByIdUseCase
import com.space.noteapp.domain.usecase.GetNotesUseCase
import com.space.noteapp.domain.usecase.InsertNoteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNotesUseCase(noteRepository = get()) }
    single { DeleteNoteUseCase(noteRepository = get()) }
    single { InsertNoteUseCase(noteRepository = get()) }
    single { GetNoteByIdUseCase(noteRepository = get()) }
}