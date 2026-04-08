package com.nmp160423183.studentproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nmp160423183.studentproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}