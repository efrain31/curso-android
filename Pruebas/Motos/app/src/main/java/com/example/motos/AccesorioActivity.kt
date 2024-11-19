package com.example.motos

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AccesorioActivity : AppCompatActivity() {
    //Instancia
    private lateinit var descripcion: TextView
    private lateinit var imagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accesorio)
//Asociar con elemento gráfico
        descripcion = findViewById(R.id.txtDescripcion)
        imagen = findViewById(R.id.imgAccesorioDetalle)
//Instancia para recibir datos de fragment
        val infoRecibida = intent.extras
//Variables para recibir los datos
        val detalle: String?
        val costo: String?
        val num: Int?
//Validar si recibe información
        if(infoRecibida != null){
//Recibir los datos del accesorio
            detalle = infoRecibida.getString("detalle")
            costo = infoRecibida.getString("costo")
            num = infoRecibida.getInt("numAccesorio")
// Colocar en TextView
            descripcion.text = "Descripción del producto:\n${detalle}\nCosto:${costo}"
//Cambio de imagen
            when(num){
                1 -> imagen.setImageResource(R.drawable.moto1)
                2 -> imagen.setImageResource(R.drawable.moto2)
                3 -> imagen.setImageResource(R.drawable.moto3)
                4 -> imagen.setImageResource(R.drawable.moto4)
                5 -> imagen.setImageResource(R.drawable.moto5)
                6 -> imagen.setImageResource(R.drawable.moto5)
            }
        } else {
            detalle = "Sin detalle"
            costo = "$0.00"
            num=1
// Colocar en TextView
            descripcion.text = "Descripción del producto:\n${detalle}\nCosto:${costo}"
        }
    }//onCreate
}//class