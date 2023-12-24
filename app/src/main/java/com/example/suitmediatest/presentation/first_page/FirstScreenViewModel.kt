package com.example.suitmediatest.presentation.first_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var name = ""
    fun isPalindrome(text: String): Boolean {
        val textFiltered = text.replace(" ", "").lowercase()
        return textFiltered == textFiltered.reversed()
    }

    fun getName (onRetrieved: (String) -> Unit) {
        viewModelScope.launch {
            repository.getName().collect{
                onRetrieved(it)
            }
        }
    }

    fun saveName(name:String, onFinished: () -> Unit) {
        viewModelScope.launch {
            async {
                repository.saveName(name)
            }.await()

            delay(1500)
            onFinished()
        }
    }
}