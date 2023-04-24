package com.example.mvp.mvp.model.cache.room

import com.example.mvp.mvp.model.cache.IUserCache
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.room.RoomGithubUser
import com.example.mvp.mvp.model.repo.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomUserCache(private val db: Database) : IUserCache {
    override fun getUsers(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(
                    roomUser.id,
                    roomUser.login,
                    roomUser.avatarUrl,
                    roomUser.reposUrl
                )
            }
        }

    override fun putUsers(users: List<GithubUser>): Completable = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}