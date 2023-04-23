package com.example.mvp.mvp.model.repo

import com.example.mvp.mvp.model.entity.GithubUsersRepositories
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getUserRepositories(): Single<List<GithubUsersRepositories>>
}