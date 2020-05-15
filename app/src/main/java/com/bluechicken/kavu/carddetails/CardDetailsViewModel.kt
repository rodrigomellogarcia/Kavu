package com.bluechicken.kavu.carddetails

import androidx.lifecycle.*

class CardDetailsViewModel : ViewModel(), LifecycleOwner {

    init {
        CardDetailsRepository.getInstance().prices.observe(this, Observer {
                it?.let {
                    prices.value = it
                }
            }
        )
    }

    var prices : MutableLiveData<MutableList<Double?>> = MutableLiveData()

    fun getLigaPrices() = CardDetailsRepository.getInstance().getAllPrices()

    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }

}