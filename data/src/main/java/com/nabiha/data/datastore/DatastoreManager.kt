package com.nabiha.data.datastore

import android.content.Context

object DatastoreManager {
    private lateinit var preferenceDatastore: PreferenceDatastore

    fun init(context: Context) {
        preferenceDatastore = PreferenceDatastore(context)
    }

    fun getPreferenceDatastore(): PreferenceDatastore {
        return preferenceDatastore
    }
}