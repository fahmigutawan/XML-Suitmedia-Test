package com.example.suitmediatest.presentation.second_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun getName (onRetrieved: (String) -> Unit) {
        viewModelScope.launch {
            repository.getName().collect{
                onRetrieved(it)
            }
        }
    }
}