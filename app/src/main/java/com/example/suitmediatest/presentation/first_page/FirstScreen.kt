package com.example.suitmediatest.presentation.first_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.FirstScreenBinding

class FirstScreen : Fragment(R.layout.first_screen) {
    lateinit var binding: FirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FirstScreenBinding.bind(view)
    }
}