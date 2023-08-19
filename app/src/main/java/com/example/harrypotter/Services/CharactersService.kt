package com.example.harrypotter.Services

import com.example.harrypotter.Entities.CharacterDetails
import com.example.harrypotter.Entities.CharacterEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {
    @GET("characters")
    fun list(): Call<List<CharacterEntity>>

    @GET("character/{id}")
    fun listDetails(@Path(value="id") todoId: String): Call<List<CharacterDetails>>

}