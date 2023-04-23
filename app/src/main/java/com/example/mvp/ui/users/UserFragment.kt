package com.example.mvp.ui.users
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvp.App
import com.example.mvp.data.GithubUser
import com.example.mvp.databinding.UserLoginLayoutBinding
import com.example.mvp.utils.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _binding: UserLoginLayoutBinding? = null
    private val binding
        get() = _binding!!

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(user, App.instance.router)
    }

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        UserLoginLayoutBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        // Nothing to do
    }

    override fun setLogin(text: String) {
        binding.textViewLogin.text = text
    }

    override fun updateList() {
        // TODO: List of repos
    }

    override fun release() {
        // TODO:
    }

    override fun backPressed() = presenter.backPressed()
}