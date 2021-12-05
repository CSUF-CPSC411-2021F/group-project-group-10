package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.myapplication.databinding.FragmentAIBinding
import java.io.File
import java.io.InputStream
import java.nio.channels.AsynchronousFileChannel.open
import java.security.AccessController.getContext

class AIFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAIBinding.inflate(layoutInflater)

        // Init of recipes array container
        val recipes = initiateFoodDB()

        binding.recipeCollector.text = recipes.elementAt(1)

        return binding.root
    }
}

// For whatever reason reading from a text file in android studios is
// incredibly unintuitive. Therefore I must manually set the array.
private fun initiateFoodDB(): MutableList<String> {

    val list = mutableListOf<String>()

    list.add(0, "Pork Chops: loin cut pork chop, white rice, crown broccoli, salt, black pepper, olive oil.") // Savory
    list.add(1, "Quesadilla: flour tortilla, shredded cheese, safflower oil.") // Savory
    list.add(2, "Jalapeno Quesadilla: flour tortilla, shredded cheese, sliced jalapeno, safflower oil, hot sauce.") // Spicy
    list.add(3, "Cookies: sugar, butter, eggs, flour, salt, chocolate chips.") // Sweet
    list.add(4, "Oatmeal Cookies: sugar, butter, eggs, flour, salt, chocolate chips, oatmeal.") // Sweet
    list.add(5, "Spicy Sweet Potato Wedges: sweet potato, habanero, serrano, sugar, lime, olive oil.") // Spicy
    list.add(6, "Wine & Herb Chicken: chicken tenders, white wine, herbs, crown broccoli, rice pilaf, salt, pepper.") // Savory
    list.add(7, "Garlic & Oil Spaghetti: pasta, garlic, red pepper, parmesan, olive oil.") // Comfort
    list.add(8, "Pho: bone broth, noodles, jalapeno, bean sprouts, cilantro, lime, seasoning, brisket.") // International
    list.add(9, "Caesar Salad: lettuce, crouton, lemon, garlic, egg, parmesan, garlic, olive oil.") // Healthy
    list.add(10, "Fruit Salad: melon, apple, berries, banana, Mango.") // Healthy

    return list
}