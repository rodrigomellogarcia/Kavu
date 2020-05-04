package com.bluechicken.kavu.cardsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluechicken.kavu.Card
import com.bluechicken.kavu.cardsearch.api_response.ScryfallResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardSearchViewModel : ViewModel() {

    val searchedCards : MutableLiveData<List<Card>> = MutableLiveData()

    fun getCards() {
        //searchedCards.value = fakeCards()
        ApiService.service.searchCardByName("Kavu").enqueue(object : Callback<ScryfallResponse> {
            override fun onResponse(call: Call<ScryfallResponse>, response: Response<ScryfallResponse>) {
                if (response.isSuccessful) {
                    val cardsFromResponse : MutableList<Card> = mutableListOf()

                    response.body()?.let {
                        for (card in it.cards) {
                            val newCard = Card(
                                name = card.name,
                                multiverseId = card.multiverse_ids.lastIndex,
                                type = card.type_line,
                                imageUrl = card.image_uris.normal,
                                imageCropUrl = card.image_uris.art_crop
                            )
                            cardsFromResponse.add(newCard)
                        }
                        searchedCards.value = cardsFromResponse
                    }

                } else {
                    Log.e("API", "Response is not successful (Error:${response.code()})")
                }
            }

            override fun onFailure(call: Call<ScryfallResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun fakeCards() : List<Card> {
        return listOf(
            Card(-1,"Fake card", "Artifact - Fake Card", null, null)
        )
    }
}