package com.example.moodimvvmexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moodimvvmexample.databinding.ActivityMainBinding
import com.example.moodimvvmexample.presentation.search.SearchMovieActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.intentBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchMovieActivity::class.java))
        }
    }
}