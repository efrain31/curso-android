package com.example.overflow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class nosotrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nosotros)

        // No es necesario agregar código adicional si solo se muestra texto estático
        val btnRegresar: Button = findViewById(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}