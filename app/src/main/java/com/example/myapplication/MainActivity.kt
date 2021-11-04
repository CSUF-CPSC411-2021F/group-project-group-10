package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val review = mutableListOf<Text>()

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = ReviewAdapter(this, review, object : ReviewAdapter.OnClickListener {

            val i = Intent(this@MainActivity, AddReviewActivity::class.java)

            )
        })

    }
}