package com.bluechicken.kavu.carddetails

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.bluechicken.kavu.liga.LigaData
import com.bluechicken.kavu.liga.LigaSet
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.*
import kotlin.collections.ArrayList


class CardDetailsRepository {

    var prices : MutableLiveData<MutableList<Double?>> = MutableLiveData()

    companion object {
        const val PRICE_MIN = "1"
        const val PRICE_AVG = "2"
        const val PRICE_MAX = "3"

        @Volatile
        private var instance : CardDetailsRepository? = null;

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CardDetailsRepository().also { instance = it }
            }
    }

    fun getAllPrices() {
        prices.value = arrayListOf()

        LigaPricesAsyncTask().execute()
    }

    fun getAllPrices2() : List<Double?> {
        var newPrices : ArrayList<Double?> = ArrayList(3)
        newPrices.add(getPrice(PRICE_MIN))
        newPrices.add(getPrice(PRICE_AVG))
        newPrices.add(getPrice(PRICE_MAX))

        return newPrices
    }



    internal inner class LigaPricesAsyncTask : AsyncTask<Void, Void , List<Double?>>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void): List<Double?> {
//            val document = Jsoup.connect("https://www.ligamagic.com.br/?view=cards/card&card=Llanowar%20Elves&show=2&mesHistoricoInicio=2019-05&mesHistoricoFim=2020-05&campo=${params}}").get()
//            val elements = document.select("script:containsData(series)")
//            val element : String = elements.dataNodes().firstOrNull().toString()
//            return buildLigaData(buildJsonStringFromElement(element))

            var newPrices : ArrayList<Double?> = ArrayList(3)
            newPrices.add(getPrice(PRICE_MIN))
            newPrices.add(getPrice(PRICE_AVG))
            newPrices.add(getPrice(PRICE_MAX))

            return newPrices

        }

        override fun onPostExecute(result: List<Double?>) {
            super.onPostExecute(result)
            if (result == null) {
                println("Deu ruim :(")
            } else {

                prices.value = result as MutableList<Double?>
                (prices.value as MutableList<Double?>).forEach {
                    println(it.toString())
                }

            }
        }

    }

    private fun buildJsonStringFromElement(element : String): String {
        var result : String = element.substringAfterLast("series")
        result = result.substringBefore("responsive").substringBeforeLast(',')
        result = "{series".plus(result).plus("}")
        return result
    }

    private fun buildLigaData(jsonString : String) : LigaData {

        val jsonObject = JSONObject(jsonString)

        val sets = mutableListOf<LigaSet>()
        val jsonSets = jsonObject.getJSONArray("series")
        for (i in 0 until jsonSets.length()) {
            val setName = jsonSets.getJSONObject(i).getString("name")
            val setPrices = jsonSets.getJSONObject(i).getJSONArray("data")
            val setPrice = setPrices.getDouble(setPrices.length() - 1)
            val listPrices = mutableListOf<Double>()
            listPrices.add(setPrice)
            sets.add(LigaSet(listPrices, setName))
        }

        return LigaData(sets)
    }

    private fun getPrice (priceTag : String) : Double? {
        val document = Jsoup.connect("https://www.ligamagic.com.br/?view=cards/card&card=Llanowar%20Elves&show=2&mesHistoricoInicio=2019-05&mesHistoricoFim=2020-05&campo=${priceTag}}").get()
        val elements = document.select("script:containsData(series)")
        val element : String = elements.dataNodes().firstOrNull().toString()
        return buildLigaData(buildJsonStringFromElement(element)).sets.firstOrNull()?.prices?.lastOrNull()
    }

}