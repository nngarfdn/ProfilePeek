package com.apps.profilepeek.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class PersonResponseItem(
    @SerializedName("address_no")
    val addressNo: String = "",
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("county")
    val county: String = "",
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("street")
    val street: String = "",
    @SerializedName("zip_code")
    val zipCode: String = ""
)