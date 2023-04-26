package com.example.mvp.mvp.presenter


import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(
    private val repo: GithubUsersRepositories
) : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        repo.forks?.let { viewState.setNumberOfForks(it) }
        viewState.setRepoName(repo.name.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}