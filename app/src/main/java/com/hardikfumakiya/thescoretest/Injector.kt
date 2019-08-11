package com.hardikfumakiya.thescoretest

/**
 * Created by Hardik Fumakiya this is injector class where injector is linked to app component
 * **/

class Injector {

    companion object {
        lateinit var appComponent: AppComponent

        fun init(app: ScoreApp) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()
        }

        fun get(): AppComponent {
            return appComponent
        }

        fun restart(app: ScoreApp) {
            init(app)
        }
    }

}