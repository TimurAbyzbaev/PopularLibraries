package com.example.mvp.mvp.presenter

import android.annotation.SuppressLint
import com.example.mvp.mvp.model.entity.GithubUser
import com.example.mvp.mvp.model.repo.IGithubUsersRepo
import com.example.mvp.ui.adapters.UserRVAdapter
import com.example.mvp.ui.adapters.UsersRVAdapter
import com.example.mvp.ui.users.IUserListPresenter
import com.example.mvp.mvp.view.list.UserItemView
import com.example.mvp.mvp.view.UsersView
import com.example.mvp.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(
    private val uiScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UsersRVAdapter.ViewHolder) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun bindView(view: UserRVAdapter.ViewHolder) {}
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }



    @SuppressLint("CheckResult")
    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}