package com.example.mvp.dagger

import com.example.mvp.mvp.presenter.MainPresenter
import com.example.mvp.mvp.presenter.UsersPresenter
import com.example.mvp.ui.activity.MainActivity
import com.example.mvp.ui.fragments.RepositoryFragment
import com.example.mvp.ui.fragments.UserFragment
import com.example.mvp.ui.fragments.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    //При выполнении практического задания это должно отсюда уйти
    fun inject(userFragment: UserFragment)
    fun inject(repositoryFragment: RepositoryFragment)
    fun inject(usersFragment: UsersFragment)

}