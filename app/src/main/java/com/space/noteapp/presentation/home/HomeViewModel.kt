package com.space.noteapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.noteapp.common.onError
import com.space.noteapp.common.onLoading
import com.space.noteapp.common.onSuccess
import com.space.noteapp.common.toResult
import com.space.noteapp.data.local.database.entitiy.NoteEntity
import com.space.noteapp.domain.usecase.DeleteNoteUseCase
import com.space.noteapp.domain.usecase.GetNotesUseCase
import com.space.noteapp.domain.usecase.InsertNoteUseCase
import com.space.noteapp.presentation.base.BaseViewModel
import com.space.noteapp.presentation.di.viewModelModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : BaseViewModel() {

    private val _homeUIState = MutableStateFlow<List<NoteUIModel>>(emptyList())
    val homeUIState get() = _homeUIState.asStateFlow()

    init {
        testInsert()

        viewModelScope.launch {
            getNotesUseCase.invoke().toResult().collectLatest {
                it.onSuccess {
                    _homeUIState.value = it.map {
                        NoteUIModel(it.id, it.title, it.description)
                    }
                }
                it.onLoading { }
                it.onError { }
            }
        }
    }

    private fun testInsert() {
        viewModelScope.launch {
            insertNoteUseCase.invoke(NoteEntity(1, "gio", "gio"))
            insertNoteUseCase.invoke(NoteEntity(2, "gio", "gio"))
            insertNoteUseCase.invoke(NoteEntity(3, "gio", "gio"))
        }
    }

    fun onItemDelete(itemId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(itemId)
        }
    }
}