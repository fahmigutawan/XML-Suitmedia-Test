package com.example.suitmediatest.data

import com.example.suitmediatest.data.data_source.DatastoreDataSource
import com.example.suitmediatest.data.data_source.RemoteDataSource
import com.example.suitmediatest.data.util.DataHandler
import com.example.suitmediatest.model.ListResponse
import io.ktor.client.call.body
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastoreDataSource: DatastoreDataSource,
    private val remoteDataSource: RemoteDataSource
){
    fun getName() = datastoreDataSource.getName()

    suspend fun saveName(name:String) = datastoreDataSource.saveName(name)

    fun getList(page:Int) = flow<DataHandler<ListResponse>> {
        emit(DataHandler.LOADING())

        try {
            val res = remoteDataSource.getList(page)

            emit(DataHandler.SUCCESS(res.body()))
        }catch (e:Exception){
            emit(DataHandler.ERROR(message = e.message.toString()))
        }
    }
}