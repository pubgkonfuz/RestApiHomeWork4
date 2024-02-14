package com.example.restapihomework4.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.restapihomework4.databinding.FragmentCharacterBinding
import com.example.restapihomework4.ui.adapter.CharacterAdapter
import com.example.restapihomework4.ui.viewModel.CharacterViewModel

class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModels()
    private var characterAdapter = CharacterAdapter()
    private lateinit var binding: FragmentCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacters()
        characterAdapter.updateData(viewModel.charactersList)
        binding.errorTextView.text = null
        setupRecyclerView()
        viewModel.uistate.observe(viewLifecycleOwner) { uiState ->
            if (!uiState.isLoading) {
                binding.progressBar.isInvisible = true
                binding.recyclerView.isVisible = true
                if (uiState.success!!.isNotEmpty()) {
                    Log.e("uiState", "onViewCreated: successful ${uiState.success}")
                    characterAdapter.updateData(uiState.success)
                    binding.errorTextView.visibility = View.GONE
                } else {
                    Log.e("uiState", "onViewCreated: error ${uiState.error}")
                    binding.errorTextView.text = uiState.error
                }
            } else {
                Log.e("uiState", "onViewCreated: loading")
                binding.progressBar.isVisible = true
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = characterAdapter
    }
}