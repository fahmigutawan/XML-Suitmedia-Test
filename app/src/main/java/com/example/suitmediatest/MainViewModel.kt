package com.example.suitmediatest

import androidx.lifecycle.ViewModel
import com.example.suitmediatest.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

}