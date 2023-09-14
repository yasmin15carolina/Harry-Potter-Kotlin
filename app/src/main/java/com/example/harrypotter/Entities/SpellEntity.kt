package com.example.harrypotter.Entities

import com.google.gson.annotations.SerializedName

class SpellEntity(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("description") var description: String = "",
) {
}