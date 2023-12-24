package com.example.suitmediatest

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.suitmediatest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

lateinit var mainViewModel: MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val viewModelTmp by viewModels<MainViewModel>()
        mainViewModel = viewModelTmp

//        val navController = findNavController(R.id.navhost)
//
//        setupActionBarWithNavController(
//            navController = navController
//        )
    }
}