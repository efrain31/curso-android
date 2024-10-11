package com.example.overflow

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class productActivity: AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

            val productsListView: ListView = findViewById(R.id.productsListView)
            // Cargar el array de productos desde los recursos
            val productos = listOf(
                Producto("DISFRAZ Cabra", "SI", "S, M, L", "$499", R.drawable.disfraces),
                Producto("DISFRAZ Ogro", "SI", "S, M, L", "$399", R.drawable.disfraces),
                Producto("DISFRAZ Perro", "SI", "S, M, L", "$550", R.drawable.disfraces),
                Producto("DISFRAZ Oso", "SI", "S, M, L", "$700", R.drawable.disfraces),
                Producto("DISFRAZ Lombriz", "SI", "S, M, L", "$899", R.drawable.disfraces)

            )

            val adapter = productAdapter(this, productos)
            productsListView.adapter = adapter


            // Crear un ArrayAdapter para mostrar los productos


            // Añadir un listener para cuando se haga clic en un item
            // Añadir un listener para cuando se haga clic en un ítem
            productsListView.setOnItemClickListener { _, _, position, _ ->
                val producto = productos[position]
                val toastMessage = "${producto.nombre}: ${producto.descripcion}, Tallas: ${producto.tallas}, Costo: ${producto.costo}"
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
            }
            val btnRegresar: Button = findViewById(R.id.btnRegresar)
            btnRegresar.setOnClickListener {
                finish()
            }

        }
}