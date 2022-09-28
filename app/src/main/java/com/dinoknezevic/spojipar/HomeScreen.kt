package com.dinoknezevic.spojipar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class HomeScreen : Fragment() {
private lateinit var promjena:Promjena
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home_screen,container,false)
        promjena = activity as Promjena
        view.findViewById<ImageButton>(R.id.play_button).setOnClickListener {
            promjena.klikPrijelaz(GameArea())
        }

        return view
    }

}