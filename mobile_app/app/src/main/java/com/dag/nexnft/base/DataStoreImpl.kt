package com.dag.nexnft.base

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStoreImpl(
    private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun <T> Save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

    suspend fun <T> Read(key: Preferences.Key<T>): T? {
        return context.dataStore.data
            .map { preferences ->
                preferences[key]
            }
            .firstOrNull()
    }

}