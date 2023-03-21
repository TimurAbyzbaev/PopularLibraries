package com.example.mvp.data

import com.example.mvp.domain.entities.GithubUser
import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    class Producer {
        fun just(): Observable<GithubUser> {
            return Observable.just(
                GithubUser("Rx_login1"),
                GithubUser("Rx_login2"),
                GithubUser("Rx_login3"),
                GithubUser("Rx_login4"),
                GithubUser("Rx_login5"))
        }
    }

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )
    fun getUsers() : List<GithubUser> {
        return repositories
    }
}
