package com.example.suitmediatest.presentation.first_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.FirstScreenBinding

class FirstScreen : Fragment(R.layout.first_screen) {
    lateinit var binding: FirstScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FirstScreenBinding.bind(view)
        val viewModel by viewModels<FirstScreenViewModel>()

        val nameInput = binding.firstScreenNameInput
        val palindromeInput = binding.firstScreenPalindromeInput
        val checkBtn = binding.firstScreenCheckBtn
        val nextBtn = binding.firstScreenNextBtn

        checkBtn.setOnClickListener {
            if (viewModel.isPalindrome(palindromeInput.text.toString())) {
                Toast.makeText(activity, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        nextBtn.setOnClickListener {
            // TODO
        }
    }
}