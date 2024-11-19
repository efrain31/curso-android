package com.example.camara

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class VerRegistrosActivity : AppCompatActivity() {
    private lateinit var listViewRegistros: ListView
    private lateinit var registros: ArrayList<Registro>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_registros)

        listViewRegistros = findViewById(R.id.listViewRegistros)

        // Obtener los registros pasados desde LectorActivity
        registros = intent.getParcelableArrayListExtra("registros") ?: ArrayList()

        // Crear un adaptador para mostrar los registros
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, registros.map { "${it.codigo} - ${it.descripcion}" })
        listViewRegistros.adapter = adapter
    } // onCreate
} // class
