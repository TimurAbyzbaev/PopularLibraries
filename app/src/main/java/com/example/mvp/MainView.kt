package com.example.mvp

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

//@AddToEndSingle - есть ещё такой алиас
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setFirstCounterText(text: String)
    fun setSecondCounterText(text: String)
    fun setThirdCounterText(text: String)
}