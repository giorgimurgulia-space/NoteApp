package com.space.noteapp.presentation.di

import com.space.noteapp.presentation.detail.DetailViewModel
import com.space.noteapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            getNotesUseCase = get(),
            deleteNoteUseCase = get(),
            insertNoteUseCase = get()
        )
    }
    viewModel {
        DetailViewModel(
            insertNoteUseCase = get(),
            getNoteByIdUseCase = get(),
            deleteNoteUseCase = get()
        )
    }

}