package com.example.suitmediatest.presentation.first_page

import androidx.lifecycle.ViewModel
import com.example.suitmediatest.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FirstScreenViewModel : ViewModel() {
    fun isPalindrome(text: String): Boolean {
        return false
    }
}