package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val listenerButton1 = View.OnClickListener {
            presenter.firstCounterClick()
        }
        val listenerButton2 = View.OnClickListener {
            presenter.secondCounterClick()
        }
        val listenerButton3 = View.OnClickListener {
            presenter.thirdCounterClick()
        }

        binding?.btnCounter1?.setOnClickListener(listenerButton1)
        binding?.btnCounter2?.setOnClickListener(listenerButton2)
        binding?.btnCounter3?.setOnClickListener(listenerButton3)
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