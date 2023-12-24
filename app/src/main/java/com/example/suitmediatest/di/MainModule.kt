package com.example.suitmediatest.di

import com.example.suitmediatest.data.Repository
import com.example.suitmediatest.data.data_source.DatastoreDataSource
import com.example.suitmediatest.data.data_source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DatastoreModule::class,
        RemoteModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideRepository(
        datastoreDataSource: DatastoreDataSource,
        remoteDataSource: RemoteDataSource
    ) = Repository(
        datastoreDataSource, remoteDataSource
    )
}