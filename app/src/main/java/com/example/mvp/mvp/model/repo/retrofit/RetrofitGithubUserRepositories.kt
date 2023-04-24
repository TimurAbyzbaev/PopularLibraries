package com.example.mvp.mvp.model.repo.retrofit

import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.cache.room.RoomRepositoriesCache
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.model.entity.room.RoomGithubRepository
import com.example.mvp.mvp.model.repo.IGithubUserRepositories
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubUserRepositories(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: RoomRepositoriesCache
) : IGithubUserRepositories {
    override fun getUserRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                        }
                }
                    ?: Single.error<List<GithubUsersRepositories>>(java.lang.RuntimeException("User has no repos url"))
                        .subscribeOn(Schedulers.io())
            } else {
                cache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}