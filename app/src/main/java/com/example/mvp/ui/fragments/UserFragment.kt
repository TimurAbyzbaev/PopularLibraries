package com.example.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.App
import com.example.mvp.mvp.model.api.ApiHolder
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.model.repo.retrofit.RetrofitGithubUserRepositories
import com.example.mvp.databinding.UserLoginLayoutBinding
import com.example.mvp.mvp.image.IImageLoader
import com.example.mvp.mvp.model.cache.room.RoomRepositoriesCache
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.AndroidNetworkStatus
import com.example.mvp.ui.image.GlideImageLoader
import com.example.mvp.navigation.AndroidScreens
import com.example.mvp.ui.activity.BackButtonListener
import com.example.mvp.ui.adapters.UserRVAdapter
import com.example.mvp.mvp.presenter.UserPresenter
import com.example.mvp.mvp.view.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            user, App.instance.router, GlideImageLoader(),
            RetrofitGithubUserRepositories(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                RoomRepositoriesCache(Database.getInstance())
            ),
            AndroidSchedulers.mainThread(),
            AndroidScreens()
        )
    }

    private var adapter: UserRVAdapter? = null
    private var _binding: UserLoginLayoutBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        UserLoginLayoutBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvRepositories.layoutManager = LinearLayoutManager(context)
        adapter = UserRVAdapter(presenter.usersRepositoriesListPresenter)
        binding.rvRepositories.adapter = adapter
    }

    override fun setLogin(text: String) {
        binding.textViewLogin.text = text
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        // TODO:
    }

    override fun setAvatar(imageLoader: IImageLoader<ImageView>, url: String) {
        imageLoader.loadInto(url, binding.ivAvatar)
    }


    override fun backPressed() = presenter.backPressed()
}