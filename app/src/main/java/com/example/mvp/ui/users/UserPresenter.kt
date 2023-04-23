package com.example.mvp.ui.users


import com.example.mvp.data.GithubUser
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(val user: GithubUser, val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let { viewState.setLogin(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}