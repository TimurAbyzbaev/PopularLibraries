package com.example.mvp.data

import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getUserRepositories(): Single<List<GithubUsersRepo>>
}