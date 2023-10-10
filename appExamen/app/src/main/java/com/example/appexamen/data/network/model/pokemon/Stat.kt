package com.example.kotlin.pokedexapp.data.network.model.pokemon

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: com.example.kotlin.pokedexapp.data.network.model.pokemon.StatX
)