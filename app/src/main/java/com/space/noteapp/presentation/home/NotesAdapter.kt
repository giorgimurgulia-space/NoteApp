package com.space.noteapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.noteapp.databinding.LayoutNoteItemBinding

class NotesAdapter(
    private val onItemClicked: ((noteId: Int) -> Unit),
    private val onDeleteClick: ((noteId: Int) -> Unit)
) : ListAdapter<NoteUIModel, NotesAdapter.MyViewHolder>(MainDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LayoutNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding).apply {
            binding.deleteImage.setOnClickListener {
                onDeleteClick.invoke(getItem(bindingAdapterPosition).id)
            }
            binding.root.setOnClickListener {
                onItemClicked.invoke(getItem(bindingAdapterPosition).id)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyViewHolder(
        private val binding: LayoutNoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteUIModel) = with(binding) {
            titleText.text = item.title
            descriptionText.text = item.description
        }
    }
}