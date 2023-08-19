package com.example.harrypotter.Entities

import com.google.gson.annotations.SerializedName

class CharacterEntity(   @SerializedName("id") var id: String = "",
                         @SerializedName("name") var name: String = "",
                         @SerializedName("house") var house: String = "",
                         @SerializedName("actor") var actor: String = "",
                         @SerializedName("image") var img: String = "") {

}