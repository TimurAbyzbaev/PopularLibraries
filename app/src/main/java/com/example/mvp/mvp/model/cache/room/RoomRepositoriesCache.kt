package com.example.mvp.mvp.model.cache.room

import com.example.mvp.mvp.model.cache.IRepositoriesCache
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.model.entity.room.RoomGithubRepository
import com.example.mvp.mvp.model.repo.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomRepositoriesCache(private val db: Database) : IRepositoriesCache {
    override fun getUserRepos(user: GithubUser): Single<List<GithubUsersRepositories>> =
        Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw java.lang.RuntimeException("No such user in cache")
            return@fromCallable db.repositoryDao.findForUser(roomUser.id).map {
                GithubUsersRepositories(it.id, it.name, it.forkCounts)
            }
        }

    override fun putUserRepos(
        user: GithubUser,
        repositories: List<GithubUsersRepositories>
    ): Completable =
        Completable.fromAction {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw java.lang.RuntimeException("No such user in cache")
            val roomRepositories = repositories.map {
                RoomGithubRepository(
                    it.id,
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepositories)
        }.subscribeOn(Schedulers.io())
}