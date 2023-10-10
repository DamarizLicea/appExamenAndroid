package com.example.appexamen.data.network

import com.example.kotlin.pokedexapp.data.network.model.PokedexObject
import com.example.kotlin.pokedexapp.data.network.model.pokemon.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPIService {
    //'https://api.themoviedb.org/3/movie/popular?language=en-US&page=1'
       @GET("movie/popular")
    suspend fun getPokemonList(
        @Query("limit") limit:Int
    ): com.example.kotlin.appExamen.data.network.model.PokedexObject

    //https://pokeapi.co/api/v2/pokemon/{number_pokemon}/
    @GET("movie/popular/{numberPokemon}")
    suspend fun getPokemonInfo(
        @Path("numberPokemon") numberPokemon:Int
    ): Pokemon
}