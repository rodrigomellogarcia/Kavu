package com.bluechicken.kavu.carddetails

import android.os.AsyncTask
import androidx.lifecycle.*

class CardDetailsViewModel : ViewModel() {

    var prices : MutableLiveData<MutableList<Double?>> = MutableLiveData()

    //fun getLigaPrices() = CardDetailsRepository.getInstance().getAllPrices()
    fun getLigaPrices() {
        prices.value = arrayListOf()
        LigaPricesAsyncTask().execute()
    }

    internal inner class LigaPricesAsyncTask : AsyncTask<Void, Void, List<Double?>>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void): List<Double?> {
            return  CardDetailsRepository.getInstance().getAllPrices2()
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

}