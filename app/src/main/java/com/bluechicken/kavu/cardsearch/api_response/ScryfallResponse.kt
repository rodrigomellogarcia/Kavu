package com.bluechicken.kavu.cardsearch.api_response

import com.google.gson.annotations.SerializedName

data class ScryfallResponse(
    @SerializedName("data")
    val cards : List<Data>,
    val has_more: Boolean,
    @SerializedName("object")
    val objectType: String,
    val total_cards: Int
)