package com.example.mvp.ui.users


import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.Toast
import com.example.mvp.App
import com.example.mvp.data.ApiHolder.api
import com.example.mvp.data.GithubUser
import com.example.mvp.data.GithubUsersRepo
import com.example.mvp.data.IGithubUsersRepo
import com.example.mvp.data.RetrofitGithubUserRepositories
import com.example.mvp.image.IImageLoader
import com.example.mvp.utils.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserPresenter(
    private val user: GithubUser,
    private val router: Router,
    private val imageLoader: IImageLoader<ImageView>,
    private val userRepositories: RetrofitGithubUserRepositories,
    private val uiScheduler: Scheduler,
    private val screens: IScreens
) : MvpPresenter<UserView>() {

    class UsersRepositoriesListPresenter : IRepositoriesListPresenter {
        val repositories = mutableListOf<GithubUsersRepo>()
        override var itemClickListener: ((IRepositoryItemView) -> Unit)? = null
        override fun getCount(): Int = repositories.size

        override fun bindView(view: UserRVAdapter.ViewHolder) {
            repositories[view.pos].name?.let { view.setRepositoryName(it) }
        }

        override fun bindView(view: UsersRVAdapter.ViewHolder) { }
    }

    val usersRepositoriesListPresenter = UsersRepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        user.login?.let { viewState.setLogin(it) }
        user.avatarUrl?.let { viewState.setAvatar(imageLoader, it) }
        loadRepositoriesList()
        usersRepositoriesListPresenter.itemClickListener = { itemView ->
            App.instance.makeToast("Forks: ${usersRepositoriesListPresenter
                .repositories[itemView.pos].forks}")
            val repo = usersRepositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(screens.repository(repo))
        }
    }

    @SuppressLint("CheckResult")
    private fun loadRepositoriesList() {
        userRepositories.getUserRepositories()
            .observeOn(uiScheduler)
            .subscribe({repositories ->
                usersRepositoriesListPresenter.repositories.clear()
                usersRepositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("########LoadRepositoriesList Error - ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}