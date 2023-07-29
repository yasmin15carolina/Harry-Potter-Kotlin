package com.example.harrypotter

import retrofit2.Call
import retrofit2.http.GET

interface CharactersService {
    @GET("characters")
    fun list(): Call<List<CharacterEntity>>
}