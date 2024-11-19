package com.example.notificaciones

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat

class PendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)

        // Establecer la barra de estado y agregar la flecha de regreso
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Asociar con componente gráfico
        val texto: TextView = findViewById(R.id.txtPending1)

        // Obtener el parámetro enviado
        val respuesta = intent.getIntExtra("accion", 1)

        // Validación de respuesta
        texto.text = if (respuesta == 1) "Seleccionaste Aceptar" else "Seleccionaste Cancelar"

        // Eliminar notificaciones
        with(NotificationManagerCompat.from(this)) {
            cancelAll()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Manejar la acción de la flecha de regreso en la barra de herramientas
        if (item.itemId == android.R.id.home) {
            Toast.makeText(this, "REGRESO", Toast.LENGTH_SHORT).show()

            // Intent para regresar a MainActivity
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
