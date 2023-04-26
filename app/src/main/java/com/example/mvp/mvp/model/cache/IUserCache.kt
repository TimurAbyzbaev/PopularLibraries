package com.example.mvp.mvp.model.cache

import com.example.mvp.mvp.model.entity.entities.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun getUsers(): Single<List<GithubUser>>
    fun putUsers(users: List<GithubUser>): Completable
}