package com.example.mvp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp.databinding.ListLayoutBinding
import com.example.mvp.image.IImageLoader

class UsersRVAdapter (val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ListLayoutBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {
        override var pos = -1
        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, vb.ivAvatar)
        }
    }
}