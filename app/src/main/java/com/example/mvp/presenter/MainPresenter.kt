package com.example.mvp.presenter

import com.example.mvp.view.MainView
import com.example.mvp.view.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter (val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }
    fun backClicked(){
        router.exit()
    }
}