package com.bluechicken.kavu.utils

object StringUtils {

    fun priceOrNull(price: Double?, nullString: String) : String {
        if (price == null) {
            return nullString
        } else {
            return "R$${price}"
        }
    }
}