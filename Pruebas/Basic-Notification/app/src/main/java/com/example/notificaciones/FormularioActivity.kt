package com.example.notificaciones

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View // Importar la clase View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat

class FormularioActivity : AppCompatActivity() {
    // Instancias
    private lateinit var nom: EditText
    private lateinit var ape: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Borrar notificación de barra de estado
        NotificationManagerCompat.from(this).cancel(NotificacionActivity.notificationId)

        // Establecer la barra de estado y agregar la flecha de regreso
        setSupportActionBar(findViewById(R.id.barraForm))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Asociar componentes
        nom = findViewById(R.id.edtNombre)
        ape = findViewById(R.id.edtApellidos)
    }

    fun agendarCita(view: View) {
        // Validar cajas de texto
        if (nom.text.isNotBlank() && ape.text.isNotBlank()) {
            Toast.makeText(applicationContext, "Cita registrada a nombre de ${nom.text} ${ape.text}.", Toast.LENGTH_SHORT).show()
            nom.text.clear() // Limpiar campo
            ape.text.clear() // Limpiar campo
            nom.requestFocus()
        } else {
            Toast.makeText(applicationContext, "¡Campos vacíos!", Toast.LENGTH_SHORT).show()
            nom.requestFocus()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Regresar a NotificacionActivity
                val intent = Intent(this, NotificacionActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                true // Indica que el evento fue manejado
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
