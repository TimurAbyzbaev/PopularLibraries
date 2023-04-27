package com.example.mvp.dagger

import com.example.mvp.dagger.user.UserSubcomponent
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
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        ImageLoadModule::class,
    ]
)
interface AppComponent {
    fun userSubcomponent(): UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    //fun inject(usersPresenter: UsersPresenter)
    //fun inject(userPresenter: UserPresenter)
    //fun inject(repositoryPresenter: RepositoryPresenter)
}