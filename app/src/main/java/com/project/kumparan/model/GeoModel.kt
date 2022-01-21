package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoModel(
    var lat: String,
    var lng: String
): Parcelable