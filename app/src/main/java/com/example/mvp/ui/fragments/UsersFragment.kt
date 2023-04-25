package com.example.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.App
import com.example.mvp.mvp.model.api.ApiHolder
import com.example.mvp.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.mvp.databinding.FragmentUsersBinding
import com.example.mvp.mvp.model.cache.room.RoomUserCache
import com.example.mvp.mvp.model.repo.room.Database
import com.example.mvp.mvp.network.AndroidNetworkStatus
import com.example.mvp.ui.image.GlideImageLoader
import com.example.mvp.navigation.AndroidScreens
import com.example.mvp.ui.activity.BackButtonListener
import com.example.mvp.ui.adapters.UsersRVAdapter
import com.example.mvp.mvp.presenter.UsersPresenter
import com.example.mvp.mvp.view.UsersView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment().apply {
            App.instance.appComponent.inject(this)
        }
    }

    @Inject lateinit var database: Database

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
    override fun backPressed() = presenter.backPressed()

}