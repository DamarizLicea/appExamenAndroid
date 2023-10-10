package com.example.kotlin.pokedexapp.data.network.model.pokemon

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.example.kotlin.pokedexapp.data.network.model.pokemon.MoveLearnMethod,
    val version_group: com.example.kotlin.pokedexapp.data.network.model.pokemon.VersionGroup
)