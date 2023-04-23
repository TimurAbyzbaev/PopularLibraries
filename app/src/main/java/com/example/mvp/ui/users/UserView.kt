package com.example.mvp.ui.users

import android.widget.ImageView
import com.example.mvp.image.IImageLoader
import com.example.mvp.ui.image.GlideImageLoader
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun init()
    fun setLogin(text: String)
    fun updateList()
    fun release()
    fun setAvatar(imageLoader: IImageLoader<ImageView>, url: String)
}