package com.example.kotlin.pokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: com.example.kotlin.pokedexapp.data.network.model.pokemon.DreamWorld,
    val home: com.example.kotlin.pokedexapp.data.network.model.pokemon.Home,
    @SerializedName("official-artwork") val official_artwork: com.example.kotlin.pokedexapp.data.network.model.pokemon.OfficialArtwork
)