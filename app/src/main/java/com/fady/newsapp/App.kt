package com.fady.newsapp

import android.app.Application
import com.fady.newsapp.data.datasource.local.LocalDataSource
import com.fady.newsapp.data.datasource.local.LocalDataSource.FILE_PREFERENCES
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalDataSource.initialize(getSharedPreferences(FILE_PREFERENCES, MODE_PRIVATE))
    }
}
