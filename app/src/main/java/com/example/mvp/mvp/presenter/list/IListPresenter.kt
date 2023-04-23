package com.example.mvp.ui.users

import com.example.mvp.ui.adapters.UserRVAdapter
import com.example.mvp.ui.adapters.UsersRVAdapter
import com.example.mvp.mvp.view.list.IItemView
import com.example.mvp.mvp.view.list.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: UsersRVAdapter.ViewHolder)
    fun bindView(view: UserRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
interface IRepositoriesListPresenter : IListPresenter<IRepositoryItemView>