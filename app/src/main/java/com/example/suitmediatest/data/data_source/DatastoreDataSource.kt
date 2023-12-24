package com.example.suitmediatest.data.data_source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class DatastoreDataSource @Inject constructor(
    private val datastore: DataStore<Preferences>
) {
}