package com.dag.nexnft

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dag.nexnft.di.appModule
import com.dag.nexnft.feature.generator.generatorDI
import com.dag.nexnft.feature.onboard.onboardDI
import com.dag.nexnft.feature.splash.splashDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(appModule)
            modules(generatorDI)
            modules(onboardDI)
            modules(splashDI)
        }
    }
}