package com.example.mvp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp.App
import com.example.mvp.databinding.ReposytoriesListLayoutBinding

class UserRVAdapter(val presenter: IRepositoriesListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ReposytoriesListLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(val vb: ReposytoriesListLayoutBinding) :
        RecyclerView.ViewHolder(vb.root), RepositoryItemView, IRepositoryItemView {
        override var pos = -1
        override fun setRepositoryName(text: String) = with(vb){
            tvRepository.text = text
        }
    }
}