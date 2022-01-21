package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressModel(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    var geo: GeoModel
): Parcelable