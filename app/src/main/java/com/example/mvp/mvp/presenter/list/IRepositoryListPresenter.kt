package com.example.mvp.ui.users

import com.example.mvp.mvp.view.list.IItemView

interface IRepositoryItemView : IItemView {
    fun setRepositoryName(text: String)
}
