package com.example.mvp.mvp.presenter


import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(
    private val repo: GithubUsersRepositories,
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