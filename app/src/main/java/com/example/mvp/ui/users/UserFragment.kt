package com.example.mvp.ui.users
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.App
import com.example.mvp.data.ApiHolder
import com.example.mvp.data.GithubUser
import com.example.mvp.data.RetrofitGithubUserRepositories
import com.example.mvp.databinding.UserLoginLayoutBinding
import com.example.mvp.image.IImageLoader
import com.example.mvp.ui.image.GlideImageLoader
import com.example.mvp.utils.AndroidScreens
import com.example.mvp.utils.BackButtonListener
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
        UserPresenter(user, App.instance.router, GlideImageLoader(),
            RetrofitGithubUserRepositories(
                ApiHolder.api,
                user.login.toString()),
            AndroidSchedulers.mainThread(),
            AndroidScreens()
        )}

    private var adapter: UserRVAdapter? = null
    private var _binding: UserLoginLayoutBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
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