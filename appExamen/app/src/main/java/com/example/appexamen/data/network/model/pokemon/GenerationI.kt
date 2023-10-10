package com.example.kotlin.pokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") val red_blue: com.example.kotlin.pokedexapp.data.network.model.pokemon.RedBlue,
    val yellow: com.example.kotlin.pokedexapp.data.network.model.pokemon.Yellow
)