package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyModel(
    var name: String,
    var catchPhrase: String,
    var bs: String
) : Parcelable