package com.example.harrypotter

import CharacterAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.harrypotter.Entities.CharacterEntity
import com.example.harrypotter.Services.CharactersService
import com.example.harrypotter.databinding.ActivityMainBinding
import com.example.harrypotter.Services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var  userListState = mutableListOf<CharacterEntity>()
        userListState.add(
            CharacterEntity(
            id = "1",
            name = "Harry Potter",
            house = "Gryffindor",
            actor = "Daniel Radcliffe",
            img = "https://example.com/harry_potter.jpg"
        )
        )
        val adapter = CharacterAdapter(this, userListState)
        binding.listview.adapter = adapter

        var nome:String="yas";


        val service = RetrofitClient.createService(CharactersService::class.java)
        val call: Call<List<CharacterEntity>> = service.list()

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
                    //binding.texto.text = list?.get(0)?.house.toString()
                    ///lista = listOf(list[0])
                    //teste = { mutableStateOf(list?.get(0)?.email.toString()) }

                    list?.let {
                        // Set the value of the userListState with the retrieved list
                        //teste.value = list[0].email
                        userListState = it.toMutableList()
                        adapter.updateData(userListState)
                    }


                }

                override fun onFailure(call: Call<List<CharacterEntity>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            })
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