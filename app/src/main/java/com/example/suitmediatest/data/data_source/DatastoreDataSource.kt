package com.example.suitmediatest.data.data_source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastoreDataSource @Inject constructor(
    private val datastore: DataStore<Preferences>
) {
    suspend fun saveName (name: String) {
        datastore.edit {
            it[stringPreferencesKey("name")] = name
        }
    }

    fun getName () = datastore.data.map {
        it[stringPreferencesKey("name")] ?: ""
    }
}