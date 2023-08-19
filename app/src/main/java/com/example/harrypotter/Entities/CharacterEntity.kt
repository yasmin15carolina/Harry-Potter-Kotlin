package com.example.harrypotter.Entities

import com.google.gson.annotations.SerializedName
import java.util.Date
import org.threeten.bp.LocalDate

class CharacterEntity(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("house") var house: String = "",
    @SerializedName("actor") var actor: String = "",
    @SerializedName("image") var img: String = "",
    @SerializedName("dateOfBirth") var birth: String = ""
) {

}