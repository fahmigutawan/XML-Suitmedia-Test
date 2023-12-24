package com.example.suitmediatest.presentation.first_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.FirstScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        viewModel.getName {
            nameInput.setText(it)
        }

        checkBtn.setOnClickListener {
            if (viewModel.isPalindrome(palindromeInput.text.toString())) {
                Toast.makeText(activity, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        nextBtn.setOnClickListener {
            if (nameInput.text.toString().isEmpty()) {
                Toast.makeText(activity, "Name Should be Filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.saveName(
                name = nameInput.text.toString(),
                onFinished = {
                    Toast.makeText(activity, "Name Successfully Saved", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_firstScreen_to_secondScreen)
                }
            )
        }
    }
}