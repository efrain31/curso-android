package com.example.notificaciones

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.MenuItem
import androidx.core.app.NotificationManagerCompat


class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
//Borrar notificación de barra de estado
        NotificationManagerCompat.from(this).apply {
            cancel(NotificacionActivity.notificationId)
        }
//Establecer la barra de estado y agregar la flecha de regreso
        setSupportActionBar(findViewById(R.id.barraInfo))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }//onCreate
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, NotificacionActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }//onOptionsItemSelected
}//class