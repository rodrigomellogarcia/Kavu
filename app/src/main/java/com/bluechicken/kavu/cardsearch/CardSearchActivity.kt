package com.bluechicken.kavu.cardsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluechicken.kavu.*
import kotlinx.android.synthetic.main.activity_card_search.*
import kotlinx.android.synthetic.main.fragment_card_search.*

class CardSearchActivity : AppCompatActivity() {
    lateinit var viewModel : CardSearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_card_search)

        updateRecyclerView(listOf())

        viewModel  = ViewModelProvider(this).get(CardSearchViewModel::class.java)
        viewModel.searchedCards.observe(this, Observer {
            it?.let {
                updateRecyclerView(it)
            }
        })

        button_search.setOnClickListener {
            viewModel.searchCardsByName(et_search.text.toString())
        }

        //viewModel.getCards()

        //addFragmentTo(R.id.content_card_search, createFragment())
        //setSupportActionBar(toolbar_card_search)

    }

    private fun updateRecyclerView(listOfCards : List<Card>) {
        with(rv_card_search_cards) {
            layoutManager = LinearLayoutManager(this@CardSearchActivity, RecyclerView.VERTICAL,false)
            setHasFixedSize(true)
            adapter = CardAdapter(listOfCards){
                val detailsIntent = CardDetailsActivity.createIntentForMe(this@CardSearchActivity, it)
                this@CardSearchActivity.startActivity(detailsIntent)
            }
        }
    }

    fun searchCardsByName(view: android.view.View?) {

    }

    /*
    fun createViewModel(): CardSearchViewModel {
        return BreedsViewModel()
    }

     */

//    fun createFragment(): CardSearchFragment {
//        return CardSearchFragment.newInstance()
//    }


}
