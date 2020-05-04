package com.bluechicken.kavu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_list_item.view.*

class CardAdapter (private val searchedCards : List<Card>): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return CardViewHolder(view)

    }

    override fun getItemCount() = searchedCards.count()

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        viewHolder.bind(searchedCards[position])
    }

    class CardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val mNameTextView = itemView.tv_card_list_item_name
        val mTypeTextView = itemView.tv_card_list_item_type
        val mCardPreviewImageView = itemView.iv_card_list_item_preview

        fun bind(card: Card) {
            mNameTextView.text = card.name
            mTypeTextView.text = card.type

            if (card.imageCropUrl != null) {
                Picasso.get().load(card.imageCropUrl).resize(100, 80).centerCrop().into(mCardPreviewImageView)
            } else {
                Picasso.get().load(Card.cardBackUrl).into(mCardPreviewImageView)
            }
        }
    }
}