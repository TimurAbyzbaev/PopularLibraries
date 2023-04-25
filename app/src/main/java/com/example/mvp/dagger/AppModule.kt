package com.example.mvp.dagger

import com.example.mvp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app():App {
        return app
    }
}