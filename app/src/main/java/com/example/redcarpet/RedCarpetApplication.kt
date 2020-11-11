package com.example.redcarpet

import android.app.Application
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//RedCarpetApplication class: This class is necessary for the hilt and you should annotate it with @HiltAndroidApp. Don't forget to add it to the manifest file in the application tag.
//this enables the hiltcode
@HiltAndroidApp
class RedCarpetApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


    }
}
