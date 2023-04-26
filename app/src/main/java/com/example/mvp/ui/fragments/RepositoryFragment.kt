package com.example.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvp.App
import com.example.mvp.databinding.FragmentRepositoryBinding
import com.example.mvp.mvp.model.entity.entities.GithubUsersRepositories
import com.example.mvp.mvp.presenter.RepositoryPresenter
import com.example.mvp.mvp.view.RepositoryView
import com.example.mvp.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), BackButtonListener, RepositoryView {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    //При выполнении практического задания это должно отсюда уйти
    //@Inject lateinit var router: Router
    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repo: GithubUsersRepositories) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repo)
            }
        }
    }

    private val presenter: RepositoryPresenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubUsersRepositories>(REPOSITORY_ARG)
                as GithubUsersRepositories
        RepositoryPresenter(repo).apply {
            App.instance.appComponent.inject(this)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
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