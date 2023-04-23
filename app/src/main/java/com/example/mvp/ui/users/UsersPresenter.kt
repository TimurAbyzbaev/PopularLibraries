package com.example.mvp.ui.users

import com.example.mvp.data.GithubUser
import com.example.mvp.data.GithubUsersRepo
import com.example.mvp.data.IGithubUsersRepo
import com.example.mvp.ui.profile.UserLogin
import com.example.mvp.utils.IScreens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UsersPresenter(
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                if (it != null) {
                    view.setLogin(it)
                }
            }
        }
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

    fun loadData() {
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


//    private val githubUserObserver = object : Observer<GithubUser> {
//        var disposable: Disposable? = null
//        override fun onSubscribe(d: Disposable) {
//            disposable = d
//            println("onSubscribe")
//        }
//
//        override fun onError(e: Throwable) {
//            println("onError: ${e.message}")
//        }
//
//        override fun onNext(s: GithubUser) {
//            usersListPresenter.users.add(s)
//            println("onNext: $s")
//        }
//
//        override fun onComplete() {
//            viewState.updateList()
//            println("onComplete")
//        }
//    }
//    private fun exec() {
//        execJust()
//    }

//    private fun execJust() {
//        producer.just()
//            .subscribe(githubUserObserver)
//    }


}