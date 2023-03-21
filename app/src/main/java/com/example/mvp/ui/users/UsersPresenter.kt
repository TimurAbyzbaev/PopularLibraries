package com.example.mvp.ui.users

import com.example.mvp.data.GithubUsersRepo
import com.example.mvp.domain.entities.GithubUser
import com.example.mvp.ui.profile.UserLogin
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    val router: Router,
    private val producer: GithubUsersRepo.Producer
    ) : MvpPresenter<UsersView>()  {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    private val githubUserObserver = object : Observer<GithubUser> {
        var disposable: Disposable? = null
        override fun onSubscribe(d: Disposable) {
            disposable = d
            println("onSubscribe")
        }

        override fun onError(e: Throwable) {
            println("onError: ${e.message}")
        }

        override fun onNext(s: GithubUser) {
            usersListPresenter.users.add(s)
            println("onNext: $s")
        }

        override fun onComplete() {
            viewState.updateList()
            println("onComplete")
        }
    }
    private fun exec() {
        execJust()
    }

    private fun execJust() {
        producer.just()
            .subscribe(githubUserObserver)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        exec()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(UserLogin.newInstance(usersListPresenter.users[itemView.pos]))

        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}