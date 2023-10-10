package com.example.kotlin.pokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white") val black_white: com.example.kotlin.pokedexapp.data.network.model.pokemon.BlackWhite
)