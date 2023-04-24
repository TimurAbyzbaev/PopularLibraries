package com.example.mvp.mvp.model.cache

import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GithubUsersRepositories>>
    fun putUserRepos(user: GithubUser, repositories: List<GithubUsersRepositories>): Completable
}