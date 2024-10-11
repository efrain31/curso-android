package com.example.overflow

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class comprasActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)

        // Simular la recuperación de datos. En tu caso, recupera los datos reales aquí.
        val sharedPreferences = getSharedPreferences("DisfracesPrefs", MODE_PRIVATE)
        val nombre = sharedPreferences.getString("Nombre", "")
        val domicilio = sharedPreferences.getString("Domicilio", "")
        val producto = sharedPreferences.getString("Producto", "")
        val talla = sharedPreferences.getString("Talla", "")
        val telefono = sharedPreferences.getString("Telefono", "")

        val purchaseDetails = "Nombre: $nombre\nProducto: $producto\nTalla: $talla\nDomicilio: $domicilio\nTeléfono: $telefono"

        // Mostrar los datos en el TextView
        val purchaseDetailsTextView: TextView = findViewById(R.id.purchaseDetailsTextView)
        purchaseDetailsTextView.text = purchaseDetails

        val btnRegresar: Button = findViewById(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            finish()
        }

    }


}