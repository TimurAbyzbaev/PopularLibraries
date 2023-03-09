package com.example.mvp.view

import com.example.mvp.view.IItemView

interface UserItemView: IItemView {
    fun setLogin(text: String)
}