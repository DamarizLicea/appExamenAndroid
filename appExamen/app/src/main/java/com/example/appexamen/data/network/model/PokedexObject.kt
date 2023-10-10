package com.example.appexamen.data.network.model

import com.google.gson.annotations.SerializedName

data class PokedexObject
    (
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: ArrayList<com.example.kotlin.appExamen.data.network.model.PokemonBase >
)
