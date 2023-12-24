package com.example.suitmediatest.data

import com.example.suitmediatest.data.data_source.DatastoreDataSource
import com.example.suitmediatest.data.data_source.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastoreDataSource: DatastoreDataSource,
    private val remoteDataSource: RemoteDataSource
){
}