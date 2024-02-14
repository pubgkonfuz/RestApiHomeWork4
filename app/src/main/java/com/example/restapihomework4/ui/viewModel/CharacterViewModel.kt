package com.example.restapihomework4.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restapihomework4.data.model.ResultsItem
import com.example.restapihomework4.data.repositories.CharacterRepository
import com.example.restapihomework4.utils.UiState

class CharacterViewModel : ViewModel() {

    var charactersList = listOf<ResultsItem>()
    private val repository = CharacterRepository()
    private val _uiState = MutableLiveData<UiState<List<ResultsItem>>>()
    val uistate: LiveData<UiState<List<ResultsItem>>> = _uiState
    fun getCharacters() {
        repository.getResultsItems(
            onResponse = { resultItem ->
                _uiState.value = UiState(success = resultItem, isLoading = false)
            },
            onFailure = { error ->
                _uiState.value = UiState(error = error.message, isLoading = false)
            }
        )
    }
    }