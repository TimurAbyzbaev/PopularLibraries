package com.example.mvp.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUsersRepositories(
    @Expose val name: String? = null,
    @Expose val forks: Int? = null
) : Parcelable

