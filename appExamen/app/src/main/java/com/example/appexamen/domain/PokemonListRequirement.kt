package com.example.kotlin.pokedexapp.domain

import com.example.kotlin.pokedexapp.data.MoviesRepository
import com.example.kotlin.pokedexapp.data.network.model.PokedexObject

class PokemonListRequirement {

    private val repository = MoviesRepository()

    suspend operator fun invoke(
        limit:Int
    ): PokedexObject? = repository.getPokemonList(limit)
}