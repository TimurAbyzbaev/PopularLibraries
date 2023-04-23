package com.example.mvp.navigation

import com.example.mvp.mvp.model.entity.GithubUser
import com.example.mvp.mvp.model.entity.GithubUsersRepositories
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen

    fun repository(repo: GithubUsersRepositories): Screen
}