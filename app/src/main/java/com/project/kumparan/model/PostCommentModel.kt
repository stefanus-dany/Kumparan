package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostCommentModel(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    var body: String
) : Parcelable