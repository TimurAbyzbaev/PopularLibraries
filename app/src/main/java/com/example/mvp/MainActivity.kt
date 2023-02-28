package com.example.mvp

import android.os.Bundle
import com.example.mvp.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter (CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnCounter1?.setOnClickListener { presenter.firstCounterClick() }
        binding?.btnCounter2?.setOnClickListener { presenter.secondCounterClick() }
        binding?.btnCounter3?.setOnClickListener { presenter.thirdCounterClick() }
    }

    override fun setFirstCounterText(text: String) {
        binding?.btnCounter1?.text = text
    }

    override fun setSecondCounterText(text: String) {
        binding?.btnCounter2?.text = text
    }

    override fun setThirdCounterText(text: String) {
        binding?.btnCounter3?.text = text
    }
}