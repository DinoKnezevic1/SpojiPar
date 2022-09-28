package com.dinoknezevic.spojipar

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(),Promjena,PrijelazEnd{
    //prediNaEnd ak ne skuzim
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = HomeScreen()
        //val secondFragment = GameArea()

        val fmanager = supportFragmentManager

        fmanager.beginTransaction().apply {
            replace(R.id.flFragment, firstFragment)
            commit()
        }

    }

    override fun klikPrijelaz(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        //val promjenaFragment = GameArea()
        transaction.replace(R.id.flFragment,fragment)
        transaction.commit()
    }

    override fun Prijedi(clicks: Int) {
        val bundle = Bundle()
        bundle.putInt("klikovi",clicks)
        EndScreen().arguments=bundle
        Log.d(ContentValues.TAG,"klikovi u mainu \n\n\n\n ${clicks}")
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,EndScreen())
        transaction.commit()
    }
    /*
    override fun predi(clicks: Int) {
        val bundle1=Bundle()
        bundle1.putInt("kliks",clicks)
        EndScreen().arguments=bundle1
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,EndScreen())
        transaction.commit()
    }
    */


}