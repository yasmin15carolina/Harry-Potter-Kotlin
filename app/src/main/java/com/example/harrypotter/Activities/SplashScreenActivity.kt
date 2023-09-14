package com.example.harrypotter.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harrypotter.CharacterActivity
import com.example.harrypotter.R
import com.example.harrypotter.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivNote.alpha = 0f;
        binding.ivNote.animate().setDuration(1500).alpha(1f).withEndAction(){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,(android.R.anim.fade_out))
            finish()
        }
    }
}