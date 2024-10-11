package com.example.notificaciones

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    // Constantes
    companion object {
        const val CHANNEL_ID = "Canal_notificacion"
        const val textTitle = "Titulo de notificación"
        const val textContent = "Este es el texto informativo de la notificación"
        const val notificationId = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Solicitar permiso para notificaciones en Android 13+
        requestNotificationPermission()

        // Crear canal de notificación
        createNotificationChannel()

        // Asociar componentes
        val btnBasica: View = findViewById(R.id.btnBasica)
        val btnToque: View = findViewById(R.id.btnToque)
        val btnAccion: View = findViewById(R.id.btnAccion)
        val btnProgreso: View = findViewById(R.id.btnProgreso)

        // Eventos
        btnBasica.setOnClickListener { notificacionBasica() }
        btnToque.setOnClickListener { notificacionToque() }
        btnAccion.setOnClickListener { notificacionBoton() }
        btnProgreso.setOnClickListener { notificacionProgress() }
    }

    // Solicitar permisos en Android 13+
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }
    }

    private fun createNotificationChannel() {
        // Crear canal de notificación para Android 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "Descripción del canal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    private fun notificacionBasica() {
        // Definir características de la notificación
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Un texto más extenso que sobrepasa de una sola línea y se debe mostrar.")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Mostrar notificación
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }

        Toast.makeText(applicationContext, "Notificación básica", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun notificacionToque() {
        // Lanzamiento de Activity con el toque a la notificación
        val intent = Intent(this, PendingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Definir características de la notificación
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText("Toque la notificación para abrir Activity")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Mostrar notificación
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    @SuppressLint("MissingPermission")
    private fun notificacionBoton() {
        // Intenciones de los botones
        val accionSi = Intent(this, PendingActivity::class.java).apply {
            putExtra("accion", 1)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val accionNo = Intent(this, PendingActivity::class.java).apply {
            putExtra("accion", 2)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntentSi: PendingIntent = PendingIntent.getActivity(
            this, 0, accionSi, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val pendingIntentNo: PendingIntent = PendingIntent.getActivity(
            this, 0, accionNo, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Definir características de la notificación
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText("Notificación con botón")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.notification_icon, getString(R.string.si), pendingIntentSi)
            .addAction(R.drawable.notification_icon, getString(R.string.no), pendingIntentNo)
            .setAutoCancel(true)

        // Mostrar notificación
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    @SuppressLint("MissingPermission")
    private fun notificacionProgress() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle(textTitle)
            setContentText("Descarga en progreso")
            setSmallIcon(R.drawable.notification_icon)
            setPriority(NotificationCompat.PRIORITY_LOW)
        }

        val PROGRESS_MAX = 100
        val PROGRESS_CURRENT = 0

        NotificationManagerCompat.from(this).apply {
            // Emitir la notificación inicial con progreso cero
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            notify(notificationId, builder.build())

            // Simular el progreso (esto debería ser en un hilo separado)
            // Actualizar el progreso conforme avanza el trabajo

            // Cuando termines, actualizar la notificación para mostrar la barra completa
            builder.setContentText("Descarga completa").setProgress(0, 0, false)
                .setTimeoutAfter(5000) // Quitar la notificación después de 5 segundos
            notify(notificationId, builder.build())
        }
    }
}
