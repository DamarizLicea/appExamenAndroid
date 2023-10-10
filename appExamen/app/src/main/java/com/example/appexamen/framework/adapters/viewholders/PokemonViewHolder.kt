package com.example.kotlin.pokedexapp.framework.adapters.viewholders

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.pokedexapp.R
import com.example.kotlin.pokedexapp.data.network.model.PokemonBase
import com.example.kotlin.pokedexapp.data.network.model.pokemon.Pokemon
import com.example.kotlin.pokedexapp.databinding.ItemPokemonBinding
import com.example.kotlin.pokedexapp.domain.PokemonInfoRequirement
import com.example.kotlin.pokedexapp.framework.views.activities.PokemonDetailActivity
import com.example.kotlin.pokedexapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonViewHolder(private val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: PokemonBase, context: Context) {
        binding.TVName.text = item.name
        getPokemonInfo(item.url, binding.IVPhoto, context)

        binding.llPokemon.setOnClickListener {
            passViewGoToPokemonDetail(item.url, context)
        }
    }

    private fun passViewGoToPokemonDetail(url: String, context: Context) {
        val intent: Intent = Intent(context, PokemonDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(Constants.URL_POKEMON, url)
        context.startActivity(intent)
    }

    private fun getPokemonInfo(url: String, imageView: ImageView, context: Context) {
        //"https://pokeapi.co/api/v2/pokemon/23/"
        var pokemonStringNumber: String = url.replace("https://pokeapi.co/api/v2/pokemon/", "")
        pokemonStringNumber = pokemonStringNumber.replace("/", "")
        val pokemonNumber: Int = Integer.parseInt(pokemonStringNumber)

        CoroutineScope(Dispatchers.IO).launch {
            val pokemonInfoRequirement = PokemonInfoRequirement()
            val result: Pokemon? = pokemonInfoRequirement(pokemonNumber)
            CoroutineScope(Dispatchers.Main).launch {
                val urlImage = result?.sprites?.other?.official_artwork?.front_default.toString()

                val requestOptions =
                    RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter().priority(Priority.HIGH)

                Glide.with(context).load(urlImage).apply(requestOptions).into(imageView)


                val card =
                    binding.llPokemon.findViewById<androidx.cardview.widget.CardView>(R.id.cvPokemon)

                val types = result?.types
                val principaltype = types?.get(0)?.type?.name.toString()

                val principalColor = getColor(principaltype)
                card.setCardBackgroundColor(Color.parseColor(principalColor))



                if (types != null) {
                    val type1Text =
                        binding.llPokemon.findViewById<androidx.appcompat.widget.AppCompatTextView>(
                            R.id.TVType1
                        )

                    val type1CV = binding.llPokemon.findViewById<androidx.cardview.widget.CardView>(
                        R.id.CVType1
                    )

                    val type2Text =
                        binding.llPokemon.findViewById<androidx.appcompat.widget.AppCompatTextView>(
                            R.id.TVType2
                        )

                    val type2CV = binding.llPokemon.findViewById<androidx.cardview.widget.CardView>(
                        R.id.CVType2
                    )


                    type1Text.text = principaltype
                    type1CV.background = generateShapeWBorder(principaltype)

                    if (types.size > 1) {
                        val secondtype = types[1].type.name.toString()
                        type2Text.text = secondtype
                        type2CV.background = generateShapeWBorder(secondtype)

                    } else {
                        type2Text.text = ""
                        type2Text.background = null
                        type2CV.background = null
                    }

                }
            }
        }
    }

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
            Color.parseColor(lightenColor(getColor(type), 0.2f))

        val shapeDrawable = GradientDrawable()
        shapeDrawable.cornerRadius = cornerRadius
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