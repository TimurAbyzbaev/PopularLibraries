package com.example.mvp.dagger

import com.example.mvp.mvp.presenter.MainPresenter
import com.example.mvp.mvp.presenter.RepositoryPresenter
import com.example.mvp.mvp.presenter.UserPresenter
import com.example.mvp.mvp.presenter.UsersPresenter
import com.example.mvp.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        AndroidScreensModule::class,
        ImageLoadModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}