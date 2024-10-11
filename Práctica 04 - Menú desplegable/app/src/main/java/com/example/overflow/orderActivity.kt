package com.example.overflow

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class orderActivity: AppCompatActivity() {

    private lateinit var nombrep: EditText
    private lateinit var domicilio: EditText
    private lateinit var producto: Spinner
    private lateinit var talla: Spinner
    private lateinit var telefono: EditText
    private lateinit var registrar: Button
    private lateinit var cancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        nombrep = findViewById(R.id.edtnombrep)
        domicilio = findViewById(R.id.edtdomicilio)
        producto = findViewById(R.id.spnproducto)
        talla = findViewById(R.id.spntalla)
        telefono = findViewById(R.id.edttelefono)
        registrar = findViewById(R.id.btnregistrar)
        cancelar = findViewById(R.id.btncancelar)

        // Configurar el spinner de productos
        val productAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.product_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        producto.adapter = productAdapter

        // Configurar el spinner de tallas
        val sizeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.size_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        talla.adapter = sizeAdapter

        // Manejar clic en el botón de registro
        registrar.setOnClickListener {
            val nombre = nombrep.text.toString()
            val domicilio = domicilio.text.toString()
            val producto = producto.selectedItem.toString()
            val talla = talla.selectedItem.toString()
            val telefono = telefono.text.toString()

            // Guardar los datos en SharedPreferences
            val sharedPreferences = getSharedPreferences("DisfracesPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("Nombre", nombre)
            editor.putString("Domicilio", domicilio)
            editor.putString("Producto", producto)
            editor.putString("Talla", talla)
            editor.putString("Telefono", telefono)
            editor.apply()

            // Confirmar al usuario que los datos han sido guardados
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // Opcional: Cerrar la Activity
            finish()

        }
        cancelar.setOnClickListener {
            // Cerrar la Activity y volver al menú principal
            finish()
        }
    }


}





