package com.bluechicken.kavu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bluechicken.kavu.R
import com.bluechicken.kavu.cardsearch.CardSearchActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToSearch(view: View?) {
        val searchIntent = Intent(this, CardSearchActivity::class.java)
        startActivity(searchIntent)
    }


    fun goToBinder(view: View?) {
        //val binderIntent = Intent(this, BinderListActivity::class.java)
        //startActivity(binderIntent)
    }

}