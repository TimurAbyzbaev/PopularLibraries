package com.example.mvp.navigation


import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.ui.fragments.RepositoryFragment
import com.example.mvp.ui.fragments.UserFragment
import com.example.mvp.ui.fragments.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
    override fun repository(repo: GithubUsersRepositories) =
        FragmentScreen { RepositoryFragment.newInstance(repo) }

}