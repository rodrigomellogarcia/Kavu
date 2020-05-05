package com.bluechicken.kavu

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card_details.*
import kotlinx.android.synthetic.main.card_list_item.view.*

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

        when {
            card.faces!!.count() > 0 -> {
                car_details_card_image.visibility = View.VISIBLE
                iv_details_card_image.visibility = View.INVISIBLE

                car_details_card_image.pageCount = card.faces!!.count()
                car_details_card_image.setImageListener { position, imageView ->
                    Picasso.get().load(card.faces!![position].imageUrl).into(imageView)
                }

                tv_details_card_name.text = card.faces!![0].name
                tv_details_card_type.text = card.faces!![0].type
                tv_details_card_text.text = card.faces!![0].oracleText

                tv_details_extra_card_name.text = card.faces!![1].name
                tv_details_extra_card_type.text = card.faces!![1].type
                tv_details_extra_card_text.text = card.faces!![1].oracleText

            }
            card.imageCropUrl != null -> {
                car_details_card_image.visibility = View.INVISIBLE
                iv_details_card_image.visibility = View.VISIBLE
                Picasso.get().load(card.imageUrl).into(iv_details_card_image)

                tv_details_card_name.text = card.name
                tv_details_card_type.text = card.type
                tv_details_card_text.text = card.oracleText

                tv_details_extra_card_name.visibility = GONE
                tv_details_extra_card_type.visibility = GONE
                tv_details_extra_card_text.visibility = GONE
            }
            else -> {
                car_details_card_image.visibility = View.INVISIBLE
                iv_details_card_image.visibility = View.VISIBLE
                Picasso.get().load(Card.cardBackUrl).into(iv_details_card_image)

                tv_details_extra_card_name.visibility = GONE
                tv_details_extra_card_type.visibility = GONE
                tv_details_extra_card_text.visibility = GONE
            }
        }

    }

    fun seeOnGatherer(view: View?) {
        val gathererUrl =
            "https://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=" + cardOnSpotlight.multiverseId?.toString()
        val gathererUri = Uri.parse(gathererUrl)
        val gathererIntent = Intent(Intent.ACTION_VIEW, gathererUri)
        if (gathererIntent.resolveActivity(packageManager) != null) {
            startActivity(gathererIntent)
        }
    }


}
