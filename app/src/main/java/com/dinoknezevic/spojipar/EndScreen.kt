package com.dinoknezevic.spojipar

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

class EndScreen : Fragment() {
private lateinit var promjena: Promjena
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_end_screen,container,false)
        var klikovi:Int? = arguments?.getInt("kliks")
        Log.d(ContentValues.TAG,"klikovi u endu \n\n\n\n ${klikovi}")
        view.findViewById<TextView>(R.id.textView5).text=klikovi.toString()

        promjena=activity as Promjena
        view.findViewById<ImageButton>(R.id.novaigra).setOnClickListener{
            promjena.klikPrijelaz(GameArea())
        }
        view.findViewById<Button>(R.id.spremi).setOnClickListener {
            promjena.klikPrijelaz(SaveScreen())
            val sureFragment = SaveScreen()
            val bundle = Bundle()
            klikovi?.let { it1 -> bundle.putInt("klikoviSpremanje", it1) }
            sureFragment.arguments = bundle
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.commit()
            fragmentTransaction?.replace(R.id.flFragment, sureFragment)
        }


        return view
    }
}