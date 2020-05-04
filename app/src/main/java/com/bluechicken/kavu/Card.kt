package com.bluechicken.kavu

data class Card(
    var multiverseId: Int,
    var name: String,
    var type: String,
    var imageUrl: String?,
    var imageCropUrl: String?
) {
    companion object {
        val cardBackUrl: String
            get() = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=0&type=card"
    }
}