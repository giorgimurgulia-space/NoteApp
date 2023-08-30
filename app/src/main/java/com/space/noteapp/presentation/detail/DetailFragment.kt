package com.space.noteapp.presentation.detail

import androidx.navigation.fragment.findNavController
import com.space.noteapp.common.collectFlow
import com.space.noteapp.databinding.FragmentDetailBinding
import com.space.noteapp.presentation.base.BaseFragment
import kotlin.reflect.KClass

class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailViewModel>(FragmentDetailBinding::inflate) {

    override val viewModelClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override fun onBind() {
        val noteId = arguments?.getInt("noteId")
        viewModel.getNote(noteId)
    }

    override fun setObserves() {
        collectFlow(viewModel.detailUIState) {
            binding.titleText.setText(it.title)
            binding.descriptionText.setText(it.description)
        }
    }

    override fun setListeners() {
        binding.backImage.setOnClickListener {
            val title = binding.titleText.text.toString()
            val description = binding.descriptionText.text.toString()
            if (viewModel.onBackButtonClick(title, description)) {
                findNavController().navigateUp()
            }
        }
    }
}