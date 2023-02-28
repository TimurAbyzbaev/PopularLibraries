package com.example.mvp

import moxy.MvpPresenter

class MainPresenter(private val model: CountersModel) : MvpPresenter<MainView>() {

    fun firstCounterClick() {
        val nextValue = model.next(0)
        viewState.setFirstCounterText(nextValue.toString())
    }

    fun secondCounterClick() {
        val nextValue = model.next(1)
        viewState.setSecondCounterText(nextValue.toString())
    }

    fun thirdCounterClick() {
        val nextValue = model.next(2)
        viewState.setThirdCounterText(nextValue.toString())
    }
}