package com.hardikfumakiya.thescoretest

import android.app.Application
import timber.log.Timber

/**
 * Created by Hardik Fumakiya this is application class bind with injector for app
 * **/
class ScoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        try {
            Timber.plant(Timber.DebugTree())

            Injector.init(this)

            Injector.get().inject(this)

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }


}