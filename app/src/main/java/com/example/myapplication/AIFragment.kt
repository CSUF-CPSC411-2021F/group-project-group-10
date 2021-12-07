package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentAIBinding
import kotlin.random.Random

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

        // Click logic for robot icon.
        // Finds a recipe for the user based off of edit text input and acquired taste AI.
        binding.aiPic.setOnClickListener {

            // Collects and stores ingredient EditText data
            val first: String = binding.ingredient1.text.toString()
            val second: String = binding.ingredient2.text.toString()
            val third: String = binding.ingredient3.text.toString()

            // Pushes ingredient data into findRecipe method and
            // collects the recipe state to display it to the user.
            val state = findRecipe(recipes, first, second, third)
            binding.recipeCollector.text = recipes.elementAt(state)
        }

        binding.info.setOnClickListener {
            Toast.makeText(context, "Insert 1-3 ingredients, do not capitalize, click on the robot to submit.", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}

// Core AI logic with implemented Local Search using states and user-driven objective values.
private fun findRecipe(list: MutableList<String>, f: String, s: String, t: String): Int {

    // Local Search values
    var currentState: Int = 0
    val objectiveVal = mutableListOf<Int>()

    // Random number value to determine tie-breaks in objective function.
    val rando = Random.nextInt(0, list.size)

    // Iterates through the list of recipes looking for
    // ingredient inclusion. Adds a value of 0 to 3 for
    // each element of the objective function array based
    // on the number of ingredients found in each list indices.
    for (item in list.indices)
    {
        // Contains all 3 ingredients.
        if (list.elementAt(item).contains(f) && list.elementAt(item).contains(s) && list.elementAt(item).contains(t))
            objectiveVal.add(item, 3)

        // Contains 2 of the ingredients
        else if ((list.elementAt(item).contains(f) && list.elementAt(item).contains(s)) || (list.elementAt(item).contains(f) && list.elementAt(item).contains(t)) || (list.elementAt(item).contains(s) && list.elementAt(item).contains(t)))
            objectiveVal.add(item, 2)

        // Contains 1 of the ingredients
        else if (list.elementAt(item).contains(f) || list.elementAt(item).contains(s) || list.elementAt(item).contains(f))
            objectiveVal.add(item, 1)

        // Contains none of the ingredients
        else
            objectiveVal.add(item, 0)
    }

    // Assigns the global max when the number of ingredients is tripped.
    val globalMax: Int = when {
        objectiveVal.contains(3) -> 3
        objectiveVal.contains(2) -> 2
        objectiveVal.contains(1) -> 1
        else -> 0
    }

    // Decides the current state, breaks potential ties with
    // "closest to but under random number"
    for (item in objectiveVal.indices)
        if (objectiveVal.elementAt(item) == globalMax && item <= rando)
            currentState = item

    // Hack to determine currentState in the event that all indices containing
    // the globalMax are higher than rando.
    if (currentState == 0)
        for (item in objectiveVal.indices)
            if (objectiveVal.elementAt(item) == globalMax && item > rando)
                currentState = item


    // Returns the indices/recipe with the highest objective value.
    return currentState
}

// For whatever reason reading from a text file in android studios is
// incredibly unintuitive. Therefore I must manually set the list.
private fun initiateFoodDB(): MutableList<String> {

    val list = mutableListOf<String>()

    list.add(0, "*No Recipe Found*") // Dud recipe, will never be used.
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
    list.add(11, "Pork Chops: loin cut pork chop, white rice, crown broccoli, salt, black pepper, olive oil.") // Savory
    list.add(12, "Pozole: tomatillo, onion, garlic, mexican oregano, pork, diced green chili, hominy.")
    list.add(13, "Chicken Teriyaki: chicken, teriyaki, soy sauce, rice, onion, bell pepper, garlic, butter, crushed chili.")
    list.add(14, "Magic Cookies: graham crackers, butter, coconut flakes, sweetened condensed milk, chocolate chips.")
    list.add(15, "Chili Relleno: poblano, white cheese, eggs, rice, beans, canola oil.")
    list.add(16, "Quiche: eggs, cheese, bacon, mushroom, leek, shallot, salt, pepper, cayenne pepper, garlic.")
    list.add(17, "Tomato Bisque: tomato, onion, basil, garlic, heavy cream, chicken stock, salt, pepper.")
    list.add(18, "Tuna Salad: tuna, pasta, celery, red onion, pickle, mustard, mayo, bell pepper.")
    list.add(19, "Tamales: lard, cornmeal, salt, marinated pork, husk.")
    list.add(20, "BLT: bacon, lettuce, tomato, sourdough bread, butter, mayo.")
    list.add(21, "Kale Smoothie: kale, cucumber, blueberries, banana.")
    list.add(22, "Stir Fry: tofu, garlic, ginger, soy sauce, spinach, bell pepper, onion, broccoli, squash.")
    list.add(23, "Meatballs: ground beef, ground pork, onion, garlic, salt, pepper, basil, milk, stale bread.")
    list.add(24, "Lemon Cream Pasta: spaghetti, heavy cream, lemon juice, lemon zest, butter, salt, pepper, garlic.")
    list.add(25, "Philly Cheese Steak: french roll, ribeye, onion, bell pepper, mayo, garlic powder, provolone cheese.")

    return list
}