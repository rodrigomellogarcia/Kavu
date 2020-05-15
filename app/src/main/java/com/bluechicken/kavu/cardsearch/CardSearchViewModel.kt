package com.bluechicken.kavu.cardsearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluechicken.kavu.Card
import com.bluechicken.kavu.cardsearch.api_response.ScryfallResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardSearchViewModel : ViewModel() {

    val searchedCards : MutableLiveData<List<Card>> = MutableLiveData()

    fun searchCardsByName(name : String) {
        ApiService.service.searchCardByName(name).enqueue(object : Callback<ScryfallResponse> {
            override fun onResponse(call: Call<ScryfallResponse>, response: Response<ScryfallResponse>) {
                if (response.isSuccessful) {
                    val cardsFromResponse : MutableList<Card> = mutableListOf()

                    response.body()?.let {
                        for (card in it.cards) {

                            // TODO: Continuar tratamento
                            var newArtUrl : String? = null
                            var newArtCrop : String? = null
                            var newOracleText : String? = null
                            card.image_uris?.let {
                                newArtCrop = it.art_crop
                                newArtUrl = it.border_crop
                                newOracleText = card.oracle_text
                            }

                            var newFaces = listOf<Card>()
                            card.card_faces?.let {
                                var nf = mutableListOf<Card>()
                                for (face in it) {
                                    val newFace = Card(
                                        name = face.name,
                                        // TODO: Mudar para ids do card pai
                                        multiverseId = null,
                                        type = face.type_line,
                                        oracleText = face.oracle_text,
                                        // TODO: alguns cards não tem imageUri (ex: Collision // Colossus.) Tratar imagens
                                        imageUrl = face.image_uris.border_crop,
                                        imageCropUrl = face.image_uris.art_crop,
                                        faces = null
                                    )
                                    nf.add(newFace)
                                }
                                newFaces = nf
                            }

                            val newCard = Card(
                                name = card.name,
                                multiverseId = card.multiverse_ids.firstOrNull(),
                                type = card.type_line,
                                oracleText = newOracleText,
                                imageUrl = newArtUrl,
                                imageCropUrl = newArtCrop,
                                faces = newFaces
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

}