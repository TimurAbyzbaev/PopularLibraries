package com.example.mvp.dagger

import android.widget.ImageView
import com.example.mvp.mvp.image.IImageLoader
import com.example.mvp.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageLoadModule {
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}