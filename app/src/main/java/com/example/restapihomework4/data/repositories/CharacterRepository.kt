package com.example.restapihomework4.data.repositories

import com.example.restapihomework4.data.model.CharactersResponse
import com.example.restapihomework4.data.model.ResultsItem
import com.example.restapihomework4.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    private val apiService = RetrofitClient.characterApiService
    fun getResultsItems(
        onResponse: (resultsItems: List<ResultsItem>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        apiService.getPosts().enqueue(object : Callback<CharactersResponse> {
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        onResponse(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}