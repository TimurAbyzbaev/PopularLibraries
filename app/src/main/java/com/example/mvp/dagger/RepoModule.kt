package com.example.mvp.dagger

import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.cache.IUserCache
import com.example.mvp.mvp.model.repo.IGithubUsersRepo
import com.example.mvp.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.mvp.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IUserCache):
            IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)
}