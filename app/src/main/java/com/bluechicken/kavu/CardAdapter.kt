package com.bluechicken.kavu

import android.net.Uri
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.activity_card_details.view.*
import kotlinx.android.synthetic.main.card_list_item.view.*

class CardAdapter (
    private val searchedCards : List<Card>,
    val onItemClickListener: ((card : Card) -> Unit)
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return CardViewHolder(view, onItemClickListener)

    }

    override fun getItemCount() = searchedCards.count()

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        viewHolder.bind(searchedCards[position])
    }

    class CardViewHolder(itemView : View, val onItemClickListener: ((card : Card) -> Unit)) : RecyclerView.ViewHolder(itemView) {

        val mNameTextView = itemView.tv_card_list_item_name
        val mTypeTextView = itemView.tv_card_list_item_type
        val mCardPreviewImageView = itemView.iv_card_list_item_preview


        fun bind(card: Card) {
            mNameTextView.text = card.name
            mTypeTextView.text = card.type

            when {
                card.faces!!.count() > 0 -> {
                    Log.w("FACES", "Faces: ${card.faces!!.count()}")
                    itemView.car_card_list_item_preview.visibility = VISIBLE
                    itemView.iv_card_list_item_preview.visibility = INVISIBLE

                    itemView.car_card_list_item_preview.pageCount = card.faces!!.count()
                    //itemView.car_card_list_item_preview.setImageListener(imageListener)
                    itemView.car_card_list_item_preview.setImageListener { position, imageView ->
                        Picasso.get().load(card.faces!![position].imageCropUrl).resize(100, 80).centerCrop().into(imageView)
                        Log.w("FACES", card.faces!![position].imageCropUrl)
                    }
                }
                card.imageCropUrl != null -> {
                    itemView.car_card_list_item_preview.visibility = INVISIBLE
                    itemView.iv_card_list_item_preview.visibility = VISIBLE
                    Picasso.get().load(card.imageCropUrl).resize(100, 80).centerCrop().into(mCardPreviewImageView)
                }
                else -> {
                    itemView.car_card_list_item_preview.visibility = INVISIBLE
                    itemView.iv_card_list_item_preview.visibility = VISIBLE
                    Picasso.get().load(Card.cardBackUrl).into(mCardPreviewImageView)
                }
            }

            itemView.setOnClickListener {
                onItemClickListener.invoke(card)
            }
        }

    }
}