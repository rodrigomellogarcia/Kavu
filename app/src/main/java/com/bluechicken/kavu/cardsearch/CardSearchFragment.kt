package com.bluechicken.kavu.cardsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluechicken.kavu.Card
import com.bluechicken.kavu.CardAdapter
import com.bluechicken.kavu.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_card_search.*

/**
 * A simple [Fragment] subclass.
 * Use the [CardSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardSearchFragment : Fragment() {

    //lateinit var viewModel: CardSearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_card_search, container, false)

        val mRecyclerView = rootView.findViewById<RecyclerView>(R.id.rv_card_search_cards)

        mRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mRecyclerView.setHasFixedSize(false)


//        with(rv_card_search_cards) {
//            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//            setHasFixedSize(true)
//            adapter = CardAdapter(fakeCards())
//        }

        // Inflate the layout for this fragment
        return rootView
    }


    companion object {

        @JvmStatic
        fun newInstance(): CardSearchFragment {
            return CardSearchFragment()
        }
    }
}
