package com.example.mvp.mvp.model.repo

import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getUserRepositories(user: GithubUser): Single<List<GithubUsersRepositories>>
}