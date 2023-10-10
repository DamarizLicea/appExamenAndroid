package com.example.kotlin.pokedexapp.data

import com.example.kotlin.pokedexapp.data.network.MoviesApiClient
import com.example.kotlin.pokedexapp.data.network.model.PokedexObject
import com.example.kotlin.pokedexapp.data.network.model.pokemon.Pokemon


class MoviesRepository() {
    private val apiMovies = MoviesApiClient()

    suspend fun getPokemonList(limit:Int): PokedexObject? = apiMovies.getPokemonList(limit)

    suspend fun getPokemonInfo(numberPokemon:Int): Pokemon?  = apiMovies.getPokemonInfo(numberPokemon)
}