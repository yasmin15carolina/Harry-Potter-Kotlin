package com.example.harrypotter.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.harrypotter.databinding.ActivityHousesBinding

class HousesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHousesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHousesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gryffindor.setOnClickListener(){
          onNavigateButtonClick("gryffindor");
        }

        binding.hufflepuff.setOnClickListener(){
            onNavigateButtonClick("hufflepuff");
        }

        binding.ravenclaw.setOnClickListener(){
            onNavigateButtonClick("ravenclaw");
        }

        binding.slytherin.setOnClickListener(){
            onNavigateButtonClick("slytherin");
        }

    }

    private fun onNavigateButtonClick( house: String) {
        try {
            val intent = Intent(this, HouseCharactersActivity::class.java)
            intent.putExtra("house", house)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("ActivityStartError", "Error starting CharacterActivity", e)
        }
    }
}