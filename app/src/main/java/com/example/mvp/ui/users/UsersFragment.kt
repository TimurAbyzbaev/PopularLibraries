package com.example.mvp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.App
import com.example.mvp.data.ApiHolder
import com.example.mvp.data.GithubUsersRepo
import com.example.mvp.data.RetrofitGithubUsersRepo
import com.example.mvp.databinding.FragmentUsersBinding
import com.example.mvp.ui.image.GlideImageLoader
import com.example.mvp.utils.AndroidScreens
import com.example.mvp.utils.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }
    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder.api),
            App.instance.router,
            AndroidScreens()
        ) }
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