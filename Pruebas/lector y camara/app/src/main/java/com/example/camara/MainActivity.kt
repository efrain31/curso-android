package com.example.camara

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    } // onCreate

    fun lector(view: View?) {
        val lectorAct = Intent(applicationContext, LectorActivity::class.java)
        startActivity(lectorAct)
    } // lector

    fun camara(view: View?) {
        val fotoAct = Intent(applicationContext, FotoActivity::class.java)
        startActivity(fotoAct)
    } // camara
} // class
