package com.example.mvp

import android.app.Application
import android.widget.Toast
import com.example.mvp.mvp.model.repo.room.Database
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App: Application() {
    companion object{
        lateinit var instance:App
    }
    //До появления Daggera положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigationHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        Database.create(this)
    }

    fun makeToast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}