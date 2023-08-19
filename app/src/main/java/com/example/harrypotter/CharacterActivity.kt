package com.example.harrypotter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.harrypotter.Entities.CharacterDetails
import com.example.harrypotter.Entities.CharacterEntity
import com.example.harrypotter.Services.CharactersService
import com.example.harrypotter.Services.RetrofitClient
import com.example.harrypotter.databinding.ActivityCharacterBinding
import com.example.harrypotter.databinding.ContentCharacterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val inflater = LayoutInflater.from(this)


        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_character)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        val characterId = intent.getSerializableExtra("characterId") as? String
        if (characterId != null) {
            val service = RetrofitClient.createService(CharactersService::class.java)
            val call: Call<List<CharacterDetails>> = service.listDetails(characterId)

            call.enqueue(
                object : Callback<List<CharacterDetails>> {
                    override fun onResponse(
                        call: Call<List<CharacterDetails>>,
                        response: Response<List<CharacterDetails>>
                    ) {
                        Toast.makeText(applicationContext, "ok", Toast.LENGTH_SHORT).show()
                        print("hello stupid");
                        var list = response.body();
                        print(list);

                        val characterInfo = list?.get(0);

                        val inflatedView = inflater.inflate(R.layout.fragment_first, null)

                        val txtCharacterName= inflatedView?.findViewById<TextView>(R.id.txtCharacterName)

                        val navHostFragment =
                            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_character)
                        val firstFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull { it is FirstFragment } as? FirstFragment

                        firstFragment?.view?.findViewById<TextView>(R.id.txtCharacterName)?.text = characterInfo?.name
                        val imageUrl = characterInfo?.img
                        if (firstFragment?.view?.findViewById<ImageView>(R.id.imgCharacter) != null) {
                            Glide.with(firstFragment)
                                .load(imageUrl)
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                                .into(findViewById<ImageView>(R.id.imgCharacter))
                        }
                        binding.fab.hide();
                    }

                    override fun onFailure(call: Call<List<CharacterDetails>>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                    }
                })
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_character)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}