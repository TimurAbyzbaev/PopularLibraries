package com.example.mvp

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания -- исправить
    fun firstCounterClick() {
        val nextValue = model.next(0)
        view.setFirstCounterText(nextValue.toString())
    }

    fun secondCounterClick() {
        val nextValue = model.next(1)
        view.setSecondCounterText(nextValue.toString())
    }

    fun thirdCounterClick() {
        val nextValue = model.next(2)
        view.setThirdCounterText(nextValue.toString())
    }
}