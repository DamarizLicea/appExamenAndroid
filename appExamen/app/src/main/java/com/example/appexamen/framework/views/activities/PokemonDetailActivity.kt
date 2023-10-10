package com.example.kotlin.pokedexapp.framework.views.activities

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.kotlin.pokedexapp.data.MoviesRepository
import com.example.kotlin.pokedexapp.data.network.model.pokemon.Pokemon
import com.example.kotlin.pokedexapp.databinding.ActivityPokemonDetailBinding
import com.example.kotlin.pokedexapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailActivity : Activity() {
    private lateinit var binding: ActivityPokemonDetailBinding
    private var pokemonUrl: String? = null
    private val pokemonRepository = MoviesRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        manageIntent()
        obtainPokemonData()
    }

    private fun initializeBinding() {
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun manageIntent() {
        if (intent != null) {
            pokemonUrl = intent.getStringExtra(Constants.URL_POKEMON)
            Log.d("Salida", pokemonUrl.toString())
        }
    }

    private fun obtainPokemonData() {

        val pokemonNumber = pokemonUrl?.split("/")?.get(6)



        CoroutineScope(Dispatchers.IO).launch {
            val pokemon =
                pokemonNumber?.let { pokemonRepository.getPokemonInfo(pokemonNumber.toInt()) }

            withContext(Dispatchers.Main) {
                pokemon?.let { updatePokemonDetails(it) }
            }

        }

    }

    private fun updatePokemonDetails(pokemon: Pokemon) {

        Glide.with(this).load(pokemon.sprites.front_default)
            .into(binding.IVPhoto)


        binding.TVName.text = pokemon.name


        val weight = pokemon.weight / 10.0
        val height = pokemon.height / 10.0

        binding.TVWeightValue.text = weight.toString() + " kg"
        binding.TVHeightValue.text = height.toString() + " m"

        binding.TVType1.background = generateShapeWBorder(pokemon.types[0].type.name)

        binding.IVBgPokemon.background = generateBackground(pokemon.types[0].type.name)

        binding.TVType1.text = pokemon.types[0].type.name
        if (pokemon.types.size > 1) {
            binding.TVType2.text = pokemon.types[1].type.name
            binding.TVType2.background = generateShapeWBorder(pokemon.types[1].type.name)
        } else {
            binding.TVType2.text = ""
        }

        binding.TVHPValue.text = pokemon.stats[0].base_stat.toString()
        binding.TVAttackValue.text = pokemon.stats[1].base_stat.toString()
        binding.TVDefenseValue.text = pokemon.stats[2].base_stat.toString()
        binding.TVSpeedValue.text = pokemon.stats[5].base_stat.toString()
        binding.TVXPValue.text = pokemon.base_experience.toString()




    }

    //TODO: Refactor this
    private fun getColor(type: String): String {
        when (type) {
            "fire" -> return "#fb6c6c"
            "water" -> return "#76bdfe"
            "grass" -> return "#48d0b0"
            "electric" -> return "#ffd86f"
            "poison" -> return "#c183c1"
            "bug" -> return "#c6d16e"
            "ground" -> return "#ebd69d"
            "fairy" -> return "#ee99ac"
            "normal" -> return "#bcbcbc"
            "fighting" -> return "#d67873"
            "psychic" -> return "#fa8581"
            "rock" -> return "#d1c17d"
            "ghost" -> return "#a292bc"
            "ice" -> return "#bce6e6"
            "dragon" -> return "#a27dfa"
            "dark" -> return "#a29288"
            "steel" -> return "#d1d1e0"
            "flying" -> return "#c6b7f5"
        }
        return "#ffffff"
    }

    private fun generateShapeWBorder(type: String): GradientDrawable {
        val secondColor = getColor(type)

        val cornerRadius = 16f
        val backgroundColor =
            Color.parseColor(lightenColor(getColor(type), -0.3f))

        val shapeDrawable = GradientDrawable()
        shapeDrawable.cornerRadius = cornerRadius


        shapeDrawable.setStroke(2, Color.parseColor(secondColor))


        shapeDrawable.setColor(backgroundColor)

        return shapeDrawable

    }

    private fun generateBackground(type: String): GradientDrawable {
        val backgroundColor =
            Color.parseColor(getColor(type))

        val shapeDrawable = GradientDrawable()

        shapeDrawable.cornerRadii = floatArrayOf(0f, 0f, 0f, 0f, 80f, 80f, 80f, 80f)



        shapeDrawable.setColor(backgroundColor)

        return shapeDrawable

    }

    fun lightenColor(hexColor: String, factor: Float): String {
        val color = Color.parseColor(hexColor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)

        val newRed = (red + (255 - red) * factor).toInt()
        val newGreen = (green + (255 - green) * factor).toInt()
        val newBlue = (blue + (255 - blue) * factor).toInt()

        val newColor = Color.rgb(newRed, newGreen, newBlue)
        return String.format("#%06X", 0xFFFFFF and newColor)
    }

}