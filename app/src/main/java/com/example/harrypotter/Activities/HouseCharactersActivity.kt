package com.example.harrypotter.Activities

import CharacterAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.harrypotter.CharacterActivity
import com.example.harrypotter.Entities.CharacterEntity
import com.example.harrypotter.R
import com.example.harrypotter.Services.CharactersService
import com.example.harrypotter.databinding.ActivityHouseCharactersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HouseCharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHouseCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        var userListState = mutableListOf<CharacterEntity>()
        /* userListState.add(
             CharacterEntity(
             id = "1",
             name = "Harry Potter",
             house = "Gryffindor",
             actor = "Daniel Radcliffe",
             img = "https://example.com/harry_potter.jpg"
         )
         )*/
        val houseName = intent.getSerializableExtra("house") as? String

        val adapter = CharacterAdapter(this, userListState)
        binding.listview.adapter = adapter
        binding.progressBar.visibility = View.VISIBLE

        var nome: String = "yas";

        if (houseName != null) {
            val service = RetrofitClient.createService(CharactersService::class.java)
            val call: Call<List<CharacterEntity>> = service.listHouseCharacters(houseName)

            call.enqueue(
                object : Callback<List<CharacterEntity>> {
                    override fun onResponse(
                        call: Call<List<CharacterEntity>>,
                        response: Response<List<CharacterEntity>>
                    ) {
                        Toast.makeText(applicationContext, "ok", Toast.LENGTH_SHORT).show()
                        print("hello stupid");
                        var list = response.body();
                        print(list);

                        list?.let {
                            // Set the value of the userListState with the retrieved list
                            //teste.value = list[0].email
                            userListState = it.toMutableList()
                            adapter.updateData(userListState)
                        }
                        binding.progressBar.visibility = View.GONE
                    }

                    override fun onFailure(call: Call<List<CharacterEntity>>, t: Throwable) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE

                    }
                })
        }
    }

    private fun onNavigateButtonClick() {
        try {
            val intent = Intent(this, CharacterActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("ActivityStartError", "Error starting CharacterActivity", e)
        }
    }


}