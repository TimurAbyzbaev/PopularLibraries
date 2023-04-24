package com.example.mvp.mvp.model.repo.retrofit

import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.room.RoomGithubUser
import com.example.mvp.mvp.model.repo.IGithubUsersRepo
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.AndroidNetworkStatus
import com.example.mvp.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//Практическое задание 1 - вытащить кэширование в отдельный класс
//RoomUserCache и внедрить его сюда через интерфейс IUserCache
class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) : IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        Single.fromCallable {
                            val roomUsers = users.map { user ->
                                RoomGithubUser(
                                    user.id ?: "",
                                    user.login ?: "",
                                    user.avatarUrl ?: "",
                                    user.reposUrl ?: ""
                                )
                            }
                            db.userDao.insert(roomUsers)
                            users
                        }
                    }
            } else {
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
            }.subscribeOn(Schedulers.io())
        }
}