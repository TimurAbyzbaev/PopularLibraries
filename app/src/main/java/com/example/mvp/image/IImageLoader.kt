package com.example.mvp.image

interface IImageLoader <T> {
    fun loadInto(url: String, container: T)
}