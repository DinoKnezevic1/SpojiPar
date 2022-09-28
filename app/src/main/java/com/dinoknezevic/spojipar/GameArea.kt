package com.dinoknezevic.spojipar

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.alpha
import androidx.fragment.app.FragmentTransaction
import java.time.format.DateTimeFormatter
import java.util.*

class GameArea : Fragment() {
    private lateinit var promjenae:PrediNaEnd
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    private var numberOfClicks:Int = 0
    private var numberOfMatches:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_game_area,container,false)

        val kartica1=view.findViewById<ImageButton>(R.id.kartica1)
        val kartica2=view.findViewById<ImageButton>(R.id.kartica2)
        val kartica3=view.findViewById<ImageButton>(R.id.kartica3)
        val kartica4=view.findViewById<ImageButton>(R.id.kartica4)
        val kartica5=view.findViewById<ImageButton>(R.id.kartica5)
        val kartica6=view.findViewById<ImageButton>(R.id.kartica6)
        val kartica7=view.findViewById<ImageButton>(R.id.kartica7)
        val kartica8=view.findViewById<ImageButton>(R.id.kartica8)
        val kartica9=view.findViewById<ImageButton>(R.id.kartica9)
        val kartica10=view.findViewById<ImageButton>(R.id.kartica10)
        val kartica11=view.findViewById<ImageButton>(R.id.kartica11)
        val kartica12=view.findViewById<ImageButton>(R.id.kartica12)
        val klikovi = view.findViewById<TextView>(R.id.clicks)
        val nastavi = view.findViewById<Button>(R.id.nastavi)
        nastavi.isClickable=false

        val images = mutableListOf(R.drawable.komp, R.drawable.bmw, R.drawable.cpu,R.drawable.grafacrvena,R.drawable.grafaplava,R.drawable.rami)
        // Add each image twice so we can create pairs
        images.addAll(images)
        // Randomize the order of images
        images.shuffle()

        buttons = listOf(kartica1,kartica2,kartica3,kartica4,kartica5,kartica6,kartica7,kartica8,kartica9,kartica10,kartica11,kartica12)

        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                //Log.i(TAG, "button clicked!!")
                numberOfClicks++
                klikovi.text=numberOfClicks.toString()
                // Update models
                updateModels(index)
                // Update the UI for the game
                updateViews()
                if (numberOfMatches==6)
                {
                    nastavi.alpha=1f
                    nastavi.isClickable=true
                }
            }
        }
        //promjenae=activity as PrediNaEnd
        nastavi.setOnClickListener {
            Log.d(TAG,"klikovi \n\n\n\n ${numberOfClicks}")
           // promjenae.predi(numberOfClicks)
            val sureFragment = EndScreen()
            val bundle = Bundle()
            bundle.putInt("kliks",numberOfClicks)
            sureFragment.arguments = bundle
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.commit()
            fragmentTransaction?.replace(R.id.flFragment, sureFragment)
        }



        return view
    }
    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.5f
                button.isClickable=false
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.poledina)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        // Error checking:
        if (card.isFaceUp) {
            //Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()
            return
        }
        // Three cases
        // 0 cards previously flipped over => restore cards + flip over the selected card
        // 1 card previously flipped over => flip over the selected card + check if the images match
        // 2 cards previously flipped over => restore cards + flip over the selected card
        if (indexOfSingleSelectedCard == null) {
            // 0 or 2 selected cards previously
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            // exactly 1 card was selected previously
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false

            }
        }
    }


    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            //Toast.makeText(this, "Match found!!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            numberOfMatches++
            //if (numberOfMatches==6){
                //endaj vrijeme
           // }
        }
    }
}