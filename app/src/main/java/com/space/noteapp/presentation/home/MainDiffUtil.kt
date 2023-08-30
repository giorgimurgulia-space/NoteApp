package com.space.noteapp.presentation.home

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class MainDiffUtil : DiffUtil.ItemCallback<NoteUIModel>() {

    override fun areItemsTheSame(oldItem: NoteUIModel, newItem: NoteUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: NoteUIModel, newItem: NoteUIModel): Boolean {
        return oldItem == newItem
    }
}