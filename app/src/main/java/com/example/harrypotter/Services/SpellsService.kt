package com.example.harrypotter.Services

import com.example.harrypotter.Entities.SpellEntity
import retrofit2.Call
import retrofit2.http.GET

interface SpellsService {
    @GET("spells")
    fun listSpells(): Call<List<SpellEntity>>
}