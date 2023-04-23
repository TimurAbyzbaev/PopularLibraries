package com.example.mvp.ui.users


import com.example.mvp.data.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(
    private val repo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        repo.forks?.let { viewState.setNumberOfForks(it)}
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}