package com.example.mvp.dagger

import com.example.mvp.navigation.AndroidScreens
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidScreensModule {
    @Singleton
    @Provides
    fun screens(): AndroidScreens = AndroidScreens()

}