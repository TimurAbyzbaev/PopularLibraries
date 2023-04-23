package com.example.mvp.utils

import com.example.mvp.data.GithubUser
import com.example.mvp.data.GithubUsersRepo
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen

    fun repository(repo: GithubUsersRepo): Screen
}