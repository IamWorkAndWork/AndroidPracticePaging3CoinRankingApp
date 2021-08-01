package com.example.data.model.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("coins")
    val cryptos: List<Crypto>? = null,
)