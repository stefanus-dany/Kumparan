package com.project.kumparan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var address: AddressModel,
    var phone: String,
    var website: String,
    var company: CompanyModel
) : Parcelable