package com.example.mvp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvp.databinding.UserLoginLayoutBinding
import com.example.mvp.domain.entities.GithubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen


class UserLogin(private val user: GithubUser): Fragment(), Screen {
    companion object {
        fun newInstance(user: GithubUser) = FragmentScreen { UserLogin(user) }
    }

    private var vb: UserLoginLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = UserLoginLayoutBinding.inflate(layoutInflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb?.textViewLogin?.text = user.login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

}