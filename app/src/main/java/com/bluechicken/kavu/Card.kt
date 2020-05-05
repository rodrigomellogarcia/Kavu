package com.bluechicken.kavu

import android.os.Parcelable
import com.bluechicken.kavu.cardsearch.api_response.CardFace
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
    var multiverseId: Int?,
    var name: String?,
    var type: String?,
    var imageUrl: String?,
    var imageCropUrl: String?,
    var oracleText: String?,
    var faces : List<Card>?
) : Parcelable {
    companion object {
        val cardBackUrl: String
            get() = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=0&type=card"
    }
}