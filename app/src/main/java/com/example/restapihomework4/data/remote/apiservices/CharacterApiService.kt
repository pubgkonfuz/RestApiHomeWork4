package com.example.restapihomework4.data.remote.apiservices

import com.example.restapihomework4.data.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET

private const val POST_END_POINT = "api/character"

interface CharacterApiService {

    @GET(POST_END_POINT)
    fun getPosts(): Call<CharactersResponse>
}