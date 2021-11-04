package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_info)

        val name = intent.getStringExtra("Name")
        val ingr = intent.getStringExtra("Ingredients")
        val step = intent.getStringExtra("Steps")

        findViewById<TextView>(R.id.infoName).apply{
            text = name.toString()
        }
        findViewById<TextView>(R.id.infoIngredients).apply{
            text = ingr.toString()
        }
        findViewById<TextView>(R.id.infoSteps).apply{
            text = step.toString()
        }
    }
}