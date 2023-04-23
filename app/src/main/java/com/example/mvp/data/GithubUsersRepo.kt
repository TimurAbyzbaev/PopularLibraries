package com.example.mvp.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUsersRepo(
    @Expose val name: String? = null,
    @Expose val forks: Int? = null
) : Parcelable

