package com.bluechicken.kavu.cardsearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private fun initRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.scryfall.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service : ScryfallService = initRetrofit().create(ScryfallService::class.java)

}