package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModel(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
) : Parcelable