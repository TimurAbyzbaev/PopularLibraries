package com.example.mvp

import android.app.Application
import android.widget.Toast
import com.example.mvp.dagger.AppComponent
import com.example.mvp.dagger.AppModule
import com.example.mvp.dagger.DaggerAppComponent

//import com.example.mvp.dagger.module.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}