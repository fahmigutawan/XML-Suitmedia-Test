package com.example.suitmediatest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suitmediatest.data.Repository
import com.example.suitmediatest.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    var selectedUser = MutableLiveData<User>()
}