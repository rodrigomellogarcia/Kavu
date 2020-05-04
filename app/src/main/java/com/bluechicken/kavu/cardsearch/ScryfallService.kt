package com.bluechicken.kavu.cardsearch

import com.bluechicken.kavu.Card
import com.bluechicken.kavu.cardsearch.api_response.ScryfallResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScryfallService {

    @GET("cards/search")
    fun searchCardByName(@Query("q") name : String) : Call<ScryfallResponse>

}