package com.example.mvp.dagger.repository.module

import com.example.mvp.App
import com.example.mvp.dagger.repository.IRepositoryScopeContainer
import com.example.mvp.dagger.repository.RepositoryScope
import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.cache.IRepositoriesCache
import com.example.mvp.mvp.model.cache.room.RoomRepositoriesCache
import com.example.mvp.mvp.model.repo.IGithubUserRepositories
import com.example.mvp.mvp.model.repo.retrofit.RetrofitGithubUserRepositories
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IRepositoriesCache {
        return RoomRepositoriesCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubUserRepositories {
        return RetrofitGithubUserRepositories(api, networkStatus, cache)
    }

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}