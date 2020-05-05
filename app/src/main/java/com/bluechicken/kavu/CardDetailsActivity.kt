package com.bluechicken.kavu

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card_details.*

class CardDetailsActivity : AppCompatActivity() {

    lateinit var cardOnSpotlight : Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        if(!intent.hasExtra("card")) {return;}

        cardOnSpotlight = intent.getParcelableExtra<Card>("card")

        bind(cardOnSpotlight);

    }

    companion object {
        fun createIntentForMe(context : Context, card: Card) : Intent {
            return Intent(context, CardDetailsActivity::class.java).apply {
                putExtra("card", card)
            }
        }
    }

    private fun bind(card : Card) {
        if (card.imageUrl != null) {
            Picasso.get().load(card.imageUrl).into(iv_details_card_image)
        } else {
            // TODO: Trocar por Kavu
            Picasso.get().load(Card.cardBackUrl).into(iv_details_card_image)
        }

        tv_details_card_name.text = card.name
        tv_details_card_type.text = card.type
        tv_details_card_text.text = card.oracleText

    }

    fun seeOnGatherer(view: View?) {
        val gathererUrl =
            "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=" + cardOnSpotlight.multiverseId?.let { it.toString() }
        val gathererUri = Uri.parse(gathererUrl)
        val gathererIntent = Intent(Intent.ACTION_VIEW, gathererUri)
        if (gathererIntent.resolveActivity(packageManager) != null) {
            startActivity(gathererIntent)
        }
    }


}
