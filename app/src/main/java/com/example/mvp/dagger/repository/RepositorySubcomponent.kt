package com.example.mvp.dagger.repository

import com.example.mvp.dagger.repository.module.RepositoryModule
import com.example.mvp.mvp.presenter.RepositoryPresenter
import com.example.mvp.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}