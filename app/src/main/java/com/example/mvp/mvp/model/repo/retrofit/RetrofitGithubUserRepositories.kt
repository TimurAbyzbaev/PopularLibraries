package com.example.mvp.mvp.model.repo.retrofit

import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.model.repo.IGithubUserRepositories
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserRepositories(
    private val api: IDataSource,
    private val login: String
    ) : IGithubUserRepositories {
    override fun getUserRepositories():
            Single<List<GithubUsersRepositories>> = api.loadUsersRepo(login).subscribeOn(Schedulers.io())
}