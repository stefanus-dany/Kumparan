package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    var albumid: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
) : Parcelable