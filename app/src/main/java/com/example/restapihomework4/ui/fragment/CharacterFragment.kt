package com.example.restapihomework4.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.restapihomework4.databinding.FragmentCharacterBinding
import com.example.restapihomework4.ui.adapter.CharacterAdapter
import com.example.restapihomework4.ui.viewModel.CharacterViewModel

class CharacterFragment : Fragment() {

    private val viewModel: CharacterViewModel by viewModels()
    private var characterAdapter = CharacterAdapter()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        search()
        subscribeToHarryPotter()
    }

    private fun initialize() {
        binding.rvRickAndMorty.adapter = characterAdapter

    }

    private fun search() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    viewModel.getCharacters(it.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun subscribeToHarryPotter() {
        viewModel.uistate.observe(viewLifecycleOwner) { uiState ->
            uiState?.let {
                if (it.success != null) {
                    characterAdapter.setRickAndMortyList(it.success)
                } else {
                Log.e("DataItem", "error")
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}