package com.example.mvp

import android.app.Application
import android.widget.Toast
import com.example.mvp.dagger.AppComponent
import com.example.mvp.dagger.AppModule
import com.example.mvp.dagger.DaggerAppComponent
import com.example.mvp.dagger.repository.IRepositoryScopeContainer
import com.example.mvp.dagger.repository.RepositorySubcomponent
import com.example.mvp.dagger.user.IUserScopeContainer
import com.example.mvp.dagger.user.UserSubcomponent

//import com.example.mvp.dagger.module.DaggerAppComponent

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }


    fun makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}