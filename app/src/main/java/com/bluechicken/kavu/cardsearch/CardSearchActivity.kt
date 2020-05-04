package com.bluechicken.kavu.cardsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluechicken.kavu.Card
import com.bluechicken.kavu.CardAdapter
import com.bluechicken.kavu.R
import com.bluechicken.kavu.addFragmentTo
import kotlinx.android.synthetic.main.activity_card_search.*
import kotlinx.android.synthetic.main.fragment_card_search.*

class CardSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_card_search)

        with(rv_card_search_cards) {
            layoutManager = LinearLayoutManager(this@CardSearchActivity, RecyclerView.VERTICAL,false)
            setHasFixedSize(true)
            adapter = CardAdapter(fakeCards())
        }


        //addFragmentTo(R.id.content_card_search, createFragment())
        //setSupportActionBar(toolbar_card_search)

    }

    /*
    fun createViewModel(): CardSearchViewModel {
        return BreedsViewModel()
    }

     */

//    fun createFragment(): CardSearchFragment {
//        return CardSearchFragment.newInstance()
//    }

    fun fakeCards() : List<Card> {
        return listOf(
            Card(-1,"Fake card", "Artifact - Fake Card", null, null)
        )
    }
}
