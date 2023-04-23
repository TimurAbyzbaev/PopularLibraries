package com.example.mvp.ui.users

import com.example.mvp.ui.users.IItemView

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}