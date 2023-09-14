package com.example.harrypotter.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.harrypotter.Fragments.HousesFragment
import com.example.harrypotter.Fragments.SpellsFragment
import com.example.harrypotter.R
import com.example.harrypotter.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding :ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HousesFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.houses -> replaceFragment(HousesFragment())
                R.id.spells -> replaceFragment(SpellsFragment())

                else ->{

                }
            }



            true
        }
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}