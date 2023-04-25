package com.example.mvp.mvp.presenter

import com.example.mvp.mvp.view.MainView
import com.example.mvp.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter () : MvpPresenter<MainView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked(){
        router.exit()
    }
}