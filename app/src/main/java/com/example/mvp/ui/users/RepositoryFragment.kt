package com.example.mvp.ui.users
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.App
import com.example.mvp.data.ApiHolder
import com.example.mvp.data.GithubUser
import com.example.mvp.data.GithubUsersRepo
import com.example.mvp.data.RetrofitGithubUserRepositories
import com.example.mvp.databinding.FragmentRepositoryBinding
import com.example.mvp.databinding.UserLoginLayoutBinding
import com.example.mvp.image.IImageLoader
import com.example.mvp.ui.image.GlideImageLoader
import com.example.mvp.utils.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), BackButtonListener, RepositoryView {
    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repo: GithubUsersRepo) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repo)
            }
        }
    }
    private val presenter: RepositoryPresenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubUsersRepo>(REPOSITORY_ARG) as GithubUsersRepo
        RepositoryPresenter(repo, App.instance.router)}

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
    override fun setRepoName(name: String) {
        binding.tvRepositoryName.text = name
    }

    override fun setNumberOfForks(number: Int) {
        binding.tvNumberOfForks.text = number.toString()
    }

    override fun init() {
        //Nothing
    }
}