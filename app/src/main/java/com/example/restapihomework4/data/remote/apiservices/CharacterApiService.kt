package com.example.restapihomework4.data.remote.apiservices

import com.example.android3_5.data.model.HarryPoterResponce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val CHARACTER = "https://api.potterdb.com/v1/characters?"

interface CharacterApiService {

    @GET(CHARACTER)
    fun getCharacter(
        @Query("filter[name_cont]") query: String
    ): Call<HarryPoterResponce>
}