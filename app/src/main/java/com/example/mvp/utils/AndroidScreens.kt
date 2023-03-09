package com.example.mvp.utils


import com.example.mvp.view.IScreens
import com.example.mvp.view.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}