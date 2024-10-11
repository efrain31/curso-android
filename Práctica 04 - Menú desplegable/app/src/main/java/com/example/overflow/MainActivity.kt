package com.example.overflow
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    private lateinit var nombre: EditText
    private lateinit var contraseña: EditText
    private lateinit var loginButton: Button
    private lateinit var exitButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById(R.id.edtnombre)
        contraseña = findViewById(R.id.edtcontra)
        loginButton = findViewById(R.id.btnlogin)
        exitButton = findViewById(R.id.btnexit)
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        loginButton.setOnClickListener {
            val nombre = nombre.text.toString()
            val contraseña = contraseña.text.toString()

            // Aquí deberías validar las credenciales del usuario

            if (nombre.isNotEmpty() && contraseña.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("efra", nombre)
                editor.putString("1234", contraseña)
                editor.apply()
                // Navegar al menú principal
                val intent = Intent(this, menuActivity::class.java)
                startActivity(intent)
            } else {
                // Mostrar algún mensaje de error al usuario
            }
        }

        exitButton.setOnClickListener {
            finishAffinity() //
        }

    }
}



