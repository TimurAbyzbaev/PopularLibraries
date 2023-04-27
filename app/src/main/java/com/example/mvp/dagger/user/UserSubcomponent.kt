package com.example.mvp.dagger.user

import com.example.mvp.dagger.repository.RepositorySubcomponent
import com.example.mvp.dagger.user.module.UserModule
import com.example.mvp.mvp.presenter.UsersPresenter
import com.example.mvp.ui.adapters.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
    //fun inject(usersRVAdapter: UsersRVAdapter)
}