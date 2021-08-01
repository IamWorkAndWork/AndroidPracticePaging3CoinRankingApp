package com.example.data.model.response


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("iconUrl")
    val iconUrl: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("rank")
    val rank: Int? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("uuid")
    val uuid: String? = null,
    @SerializedName("volume")
    val volume: Long? = null,
)