package com.example.mvp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.App
import com.example.mvp.databinding.UserLoginLayoutBinding
import com.example.mvp.mvp.image.IImageLoader
import com.example.mvp.mvp.model.entity.entities.GithubUser
import com.example.mvp.mvp.presenter.UserPresenter
import com.example.mvp.mvp.view.UserView
import com.example.mvp.ui.activity.BackButtonListener
import com.example.mvp.ui.adapters.UserRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    //@Inject lateinit var database: Database
    //@Inject lateinit var router: Router

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
            //App.instance.appComponent.inject(this)
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser

        UserPresenter(user).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var _binding: UserLoginLayoutBinding? = null
    private val binding
        get() = _binding!!

    private var adapter: UserRVAdapter? = null


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

    @SuppressLint("NotifyDataSetChanged")
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