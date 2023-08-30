package com.space.noteapp.presentation.detail

import androidx.lifecycle.viewModelScope
import com.space.noteapp.data.local.database.entitiy.NoteEntity
import com.space.noteapp.domain.usecase.DeleteNoteUseCase
import com.space.noteapp.domain.usecase.GetNoteByIdUseCase
import com.space.noteapp.domain.usecase.InsertNoteUseCase
import com.space.noteapp.presentation.base.BaseViewModel
import com.space.noteapp.presentation.home.NoteUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random.Default.nextInt

class DetailViewModel(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    private val _detailUIState = MutableStateFlow(NoteUIModel())
    val detailUIState get() = _detailUIState.asStateFlow()


    fun getNote(noteId: Int?) {
        if ((noteId != null) && (noteId > 0)) {
            viewModelScope.launch {
                val note = getNoteByIdUseCase.invoke(noteId)
                _detailUIState.value = NoteUIModel(note.id, note.title, note.description)
            }
        }
    }

    fun onBackButtonClick(title: String, description: String): Boolean {
        if (title.isNullOrEmpty() && description.isNullOrEmpty()) {
            deleteNote()
        } else {
            insertNote(title, description)
        }
        return true
    }

    private fun deleteNote() {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(_detailUIState.value.id)
        }
    }

    private fun insertNote(title: String, description: String) {
        viewModelScope.launch {
            insertNoteUseCase.invoke(
                NoteEntity(
                    getId(),
                    title,
                    description
                )
            )
        }
    }

    private fun getId(): Int {
        if (_detailUIState.value.id < 0)
            return ((0..1000).random())
        else
            return _detailUIState.value.id
    }
}