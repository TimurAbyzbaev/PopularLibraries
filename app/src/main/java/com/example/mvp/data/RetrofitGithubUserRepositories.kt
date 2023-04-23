package com.example.mvp.data

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserRepositories(
    private val api: IDataSource,
    private val login: String
    ) : IGithubUserRepositories {
    override fun getUserRepositories():
            Single<List<GithubUsersRepo>> = api.loadUsersRepo(login).subscribeOn(Schedulers.io())
}