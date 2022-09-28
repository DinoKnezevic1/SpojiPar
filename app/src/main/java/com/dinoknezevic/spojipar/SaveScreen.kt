package com.dinoknezevic.spojipar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text


class SaveScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_save_screen,container,false)
        var klikoviSpremanje:Int? = arguments?.getInt("klikoviSpremanje")
        view.findViewById<TextView>(R.id.rezultatspremi).text=klikoviSpremanje.toString()

        //spremanje u bazu sad
        return view
    }


}