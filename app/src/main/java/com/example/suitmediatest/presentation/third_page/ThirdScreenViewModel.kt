package com.example.suitmediatest.presentation.third_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.data.Repository
import com.example.suitmediatest.data.util.DataHandler
import com.example.suitmediatest.model.ListResponse
import com.example.suitmediatest.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdScreenViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel(){
    val listState = MutableLiveData<DataHandler<ListResponse>>(DataHandler.LOADING())
    val listData = MutableLiveData<List<User>>(listOf())
    val pageNow = MutableLiveData(1)
    val endOfPagination = MutableLiveData(false)

    fun getList(page:Int){
        viewModelScope.launch {
            repository.getList(page).collect{
                listState.postValue(it)

                if(it is DataHandler.SUCCESS){
                    listData.postValue((listData.value ?: listOf()) + (it.data?.data ?: listOf()))
                    if((listData.value ?:  listOf()).size != 0 && (listData.value ?:  listOf()).size % 10 != 0){
                        endOfPagination.postValue(true)
                    }
                }
            }
        }
    }

    init {
        getList(1)
    }
}