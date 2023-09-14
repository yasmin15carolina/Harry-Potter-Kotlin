package com.example.harrypotter.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.harrypotter.Activities.HouseCharactersActivity
import com.example.harrypotter.R
import com.example.harrypotter.databinding.FragmentHousesBinding

class HousesFragment : Fragment() {

    private lateinit var binding: FragmentHousesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.navigationBarColor =
            ContextCompat.getColor(requireContext(), R.color.black)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.transparent)

        binding.gryffindor.setOnClickListener {
            onNavigateButtonClick("gryffindor")
        }

        binding.hufflepuff.setOnClickListener {
            onNavigateButtonClick("hufflepuff")
        }

        binding.ravenclaw.setOnClickListener {
            onNavigateButtonClick("ravenclaw")
        }

        binding.slytherin.setOnClickListener {
            onNavigateButtonClick("slytherin")
        }
    }

    private fun onNavigateButtonClick(house: String) {
        try {
            val intent = Intent(requireContext(), HouseCharactersActivity::class.java)
            intent.putExtra("house", house)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("FragmentStartError", "Error starting HouseCharactersActivity", e)
        }
    }
}
