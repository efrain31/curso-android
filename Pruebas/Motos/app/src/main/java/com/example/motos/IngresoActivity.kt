package com.example.motos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IngresoActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText

    // Arreglo de usuarios y contraseñas
    private val usuarios = arrayOf(
        Pair("Carlos", "1234"),
        Pair("Mauricio", "1234"),
        Pair("Aguila",  "1234")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ingreso)
        //Asociar instancias con componentes gráficos
        email = findViewById(R.id.edtCorreo)
        password = findViewById(R.id.edtContrasena)

    }
    fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnIngresar -> aceptar()
            R.id.btnlimpiar -> cancelar()
        }
    }


    private fun aceptar() {
        // Validar que existan datos
        if (email.text.isNotBlank() && email.text.isNotEmpty() &&
            password.text.isNotBlank() && password.text.isNotEmpty()) {

            val inputCorreo = email.text.toString()
            val inputContrasena = password.text.toString()
            val usr = Usuario(inputCorreo, inputContrasena, true)

            // Verificar si el usuario y la contraseña son válidos
            val usuarioValido = usuarios.any { it.first == inputCorreo && it.second == inputContrasena }

            if (usuarioValido) {

                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Capturar información", Toast.LENGTH_LONG).show()
        }
    } // ingresar()


    private fun cancelar() {
        Toast.makeText(this,"Termina aplicación.", Toast.LENGTH_SHORT).show()
        finish()
    }//cancelar


}