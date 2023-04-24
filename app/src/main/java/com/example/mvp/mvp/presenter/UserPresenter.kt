package com.example.mvp.mvp.presenter


import android.annotation.SuppressLint
import android.widget.ImageView
import com.example.mvp.App
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.model.repo.retrofit.RetrofitGithubUserRepositories
import com.example.mvp.mvp.image.IImageLoader
import com.example.mvp.ui.adapters.UserRVAdapter
import com.example.mvp.ui.adapters.UsersRVAdapter
import com.example.mvp.ui.users.IRepositoriesListPresenter
import com.example.mvp.ui.users.IRepositoryItemView
import com.example.mvp.mvp.view.UserView
import com.example.mvp.navigation.IScreens
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
        val repositories = mutableListOf<GithubUsersRepositories>()
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