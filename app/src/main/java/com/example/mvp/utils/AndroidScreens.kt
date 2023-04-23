package com.example.mvp.utils


import com.example.mvp.data.GithubUser
import com.example.mvp.data.GithubUsersRepo
import com.example.mvp.ui.users.RepositoryFragment
import com.example.mvp.ui.users.UserFragment
import com.example.mvp.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
    override fun repository(repo: GithubUsersRepo) = FragmentScreen { RepositoryFragment.newInstance(repo) }

}