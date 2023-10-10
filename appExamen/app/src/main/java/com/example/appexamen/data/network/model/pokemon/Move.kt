package com.example.kotlin.pokedexapp.data.network.model.pokemon

data class Move(
    val move: com.example.kotlin.pokedexapp.data.network.model.pokemon.MoveX,
    val version_group_details: List<com.example.kotlin.pokedexapp.data.network.model.pokemon.VersionGroupDetail>
)