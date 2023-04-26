package com.example.mvp.mvp.model.repo.retrofit

import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.cache.IUserCache
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.repo.IGithubUsersRepo
import com.example.mvp.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IUserCache
) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        cache.putUsers(users).toSingleDefault(users)
                    }
            } else {
                cache.getUsers()
            }.subscribeOn(Schedulers.io())
        }
}