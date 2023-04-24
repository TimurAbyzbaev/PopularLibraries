package com.example.mvp.mvp.model.repo.retrofit

import com.example.mvp.mvp.model.api.IDataSource
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.model.entity.room.RoomGithubRepository
import com.example.mvp.mvp.model.repo.IGithubUserRepositories
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


//Практическое задание 1 - вытащить кэширование в отдельный класс
//RoomRepositoriesCache и внедрить его сюда через интерфейс IRepositoriesCache
class RetrofitGithubUserRepositories(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) : IGithubUserRepositories {
    override fun getUserRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let {
                                    db.userDao.findByLogin(it)
                                } ?: throw java.lang.RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGithubRepository(
                                        it.id ?: "",
                                        it.name ?: "",
                                        it.forks ?: 0,
                                        roomUser.id
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                }
                    ?: Single.error<List<GithubUsersRepositories>>(java.lang.RuntimeException("User has no repos url"))
                        .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let {
                        db.userDao.findByLogin(it)
                    } ?: throw java.lang.RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id).map {
                        GithubUsersRepositories(it.id, it.name, it.forkCounts)
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}