package com.space.noteapp.presentation.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.noteapp.common.collectFlow
import com.space.noteapp.databinding.FragmentHomeBinding
import com.space.noteapp.presentation.base.BaseFragment
import kotlin.reflect.KClass

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val adapter = NotesAdapter(
        onItemClicked = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        },
        onDeleteClick = {
            viewModel.onItemDelete(it)
        }
    )

    override fun onBind() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.mainRecyclerView.adapter = adapter
    }

    override fun setObserves() {
        collectFlow(viewModel.homeUIState) {
            adapter.submitList(it)
        }
    }

    override fun setListeners() {
        binding.plusImage.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(-1)
            )
        }
    }
}