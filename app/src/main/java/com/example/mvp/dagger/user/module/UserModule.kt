package com.example.mvp.dagger.user.module

import com.example.mvp.App
import com.example.mvp.dagger.user.IUserScopeContainer
import com.example.mvp.dagger.user.UserScope
import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.cache.IUserCache
import com.example.mvp.mvp.model.cache.room.RoomUserCache
import com.example.mvp.mvp.model.repo.IGithubUsersRepo
import com.example.mvp.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @Provides
    fun usersCache(database: Database): IUserCache {
        return RoomUserCache(database)
    }

    @UserScope
    @Provides
    open fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache
    ): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus, cache)
    }

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}