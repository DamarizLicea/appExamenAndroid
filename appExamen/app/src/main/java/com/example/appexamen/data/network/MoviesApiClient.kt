package com.example.kotlin.pokedexapp.data.network

import com.example.appexamen.data.network.PokemonAPIService
import com.example.appexamen.data.network.model.PokedexObject
import com.example.kotlin.appExamen.data.network.NetworkModuleDI
import com.example.kotlin.pokedexapp.data.network.model.pokemon.Pokemon

class MoviesApiClient {
    private lateinit var api: PokemonAPIService

    suspend fun getPokemonList(limit:Int): PokedexObject?{
        api = NetworkModuleDI()
        return try{
            api.getPokemonList(limit)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun getPokemonInfo(numberPokemon:Int): Pokemon? {
        api = NetworkModuleDI()
        return try{
            api.getPokemonInfo(numberPokemon)
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            null
        }
    }
}