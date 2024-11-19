package com.example.appmenulateral2.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appmenulateral2.R
import androidx.activity.result.contract.ActivityResultContracts

class Contacto : Fragment() {

    private var selectedTool: String? = null
    private val CHANNEL_ID = "contacto_notification_channel"
    private val NOTIFICATION_ID = 1

    // Launcher para solicitar permiso
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // El usuario concedió el permiso
            Toast.makeText(requireContext(), "Permiso para notificaciones concedido", Toast.LENGTH_SHORT).show()
        } else {
            // El usuario rechazó el permiso
            Toast.makeText(requireContext(), "Permiso para notificaciones rechazado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragmento
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        // Referencias a los campos del formulario
        val nombreField = view.findViewById<EditText>(R.id.nombre)
        val apellidoField = view.findViewById<EditText>(R.id.apellido)
        val direccionField = view.findViewById<EditText>(R.id.direccion)
        val telefonoField = view.findViewById<EditText>(R.id.telefono)
        val listViewHerramientas = view.findViewById<ListView>(R.id.listViewHerramientas)

        val btnRegistrar = view.findViewById<Button>(R.id.btnRegistrar)
        val btnLimpiar = view.findViewById<Button>(R.id.btnLimpiar)
        val btnRegresar = view.findViewById<Button>(R.id.btnRegresar)

        // Tools array
        val herramientas = arrayOf("Martillo", "Destornillador", "Taladro", "Sierra", "Llave inglesa")

        // Setting up ListView adapter
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_single_choice, herramientas)
        listViewHerramientas.adapter = adapter
        listViewHerramientas.choiceMode = ListView.CHOICE_MODE_SINGLE

        // Set item click listener
        listViewHerramientas.setOnItemClickListener { _, _, position, _ ->
            selectedTool = herramientas[position]
        }

        // Crear canal de notificación
        createNotificationChannel()

        // Acción al presionar "Registrar"
        btnRegistrar.setOnClickListener {
            val nombre = nombreField.text.toString()
            val apellido = apellidoField.text.toString()
            val direccion = direccionField.text.toString()
            val telefono = telefonoField.text.toString()

            if (nombre.isNotEmpty() && apellido.isNotEmpty() && direccion.isNotEmpty() && telefono.isNotEmpty() && selectedTool != null) {
                // Crear objeto con los datos
                val contacto = ContactoData(nombre, apellido, direccion, telefono, selectedTool!!)

                // Mostrar mensaje en un Toast
                Toast.makeText(requireContext(), "Registrado: ${contacto.nombre} ${contacto.apellido} con herramienta ${contacto.herramientaComprada}", Toast.LENGTH_LONG).show()

                // Enviar notificación
                sendNotification(contacto)

            } else {
                // Si falta algún campo, mostrar mensaje de error
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción al presionar "Limpiar"
        btnLimpiar.setOnClickListener {
            // Limpiar todos los campos
            nombreField.text.clear()
            apellidoField.text.clear()
            direccionField.text.clear()
            telefonoField.text.clear()
            listViewHerramientas.clearChoices()
            selectedTool = null

            // Mostrar mensaje de que se ha limpiado el formulario
            Toast.makeText(requireContext(), "Formulario limpiado", Toast.LENGTH_SHORT).show()
        }

        // Acción al presionar "Regresar"
        btnRegresar.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }

    // Método para enviar notificación
    private fun sendNotification(contacto: ContactoData) {
        // Verificar si el permiso está concedido
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED) {

            // Permiso concedido, procede a enviar la notificación
            val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Nuevo Registro")
                .setContentText("Registrado: ${contacto.nombre} ${contacto.apellido} con herramienta ${contacto.herramientaComprada}")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            with(NotificationManagerCompat.from(requireContext())) {
                notify(NOTIFICATION_ID, notification)
            }
        } else {
            // Permiso no concedido, solicitar permiso
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // Método para crear canal de notificación (solo para Android 8.0 y superior)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Contacto Registro"
            val descriptionText = "Notificaciones para registros de contacto"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Registrar el canal en el sistema
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

// Clase para contener los datos del contacto
data class ContactoData(val nombre: String, val apellido: String, val direccion: String, val telefono: String, val herramientaComprada: String)
