package com.example.redcarpet

import android.app.Application
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
@HiltAndroidApp
class RedCarpetApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


    }
}