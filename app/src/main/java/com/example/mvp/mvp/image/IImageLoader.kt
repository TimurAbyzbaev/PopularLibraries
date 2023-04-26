package com.example.mvp.mvp.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}