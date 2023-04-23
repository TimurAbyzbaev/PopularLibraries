package com.example.mvp.ui.users

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: UsersRVAdapter.ViewHolder)
    fun bindView(view: UserRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
interface IRepositoriesListPresenter : IListPresenter<IRepositoryItemView>