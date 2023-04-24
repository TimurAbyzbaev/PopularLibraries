package com.example.mvp.mvp.model.repo

import com.example.mvp.mvp.model.entity.entities.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}