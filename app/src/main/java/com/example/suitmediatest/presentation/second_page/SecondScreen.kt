package com.example.suitmediatest.presentation.second_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.SecondScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreen: Fragment(R.layout.second_screen) {
    lateinit var binding: SecondScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SecondScreenBinding.bind(view)
        binding.secondscreenAppbar.appbarTitle.setText("Second Screen")
        val viewModel by viewModels<SecondScreenViewModel>()

        val name = binding.secondscreenNameTv
        val backBtn = binding.secondscreenAppbar.backBtn
        val chooseBtn = binding.secondscreenChooseBtn

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        chooseBtn.setOnClickListener {
            findNavController().navigate(R.id.action_secondScreen_to_thirdScreen)
        }

        viewModel.getName {
            name.setText(it)
        }
    }
}