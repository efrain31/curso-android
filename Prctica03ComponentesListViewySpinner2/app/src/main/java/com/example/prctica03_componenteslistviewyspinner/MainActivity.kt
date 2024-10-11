package com.example.prctica03_componenteslistviewyspinner

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prctica03_componenteslistviewyspinner.model.Concierto
import com.example.prctica03_componenteslistviewyspinner.ui.theme.Práctica03ComponentesListViewYSpinnerTheme

class MainActivity : ComponentActivity() {
    private var conciertos = mutableListOf<Concierto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar con algunos conciertos de prueba
        if (conciertos.isEmpty()) {
            conciertos.add(Concierto("Artista 1", "2023-10-01", "Lugar 1", 100.0, 50))
            conciertos.add(Concierto("Artista 2", "2023-10-02", "Lugar 2", 150.0, 75))
        }

        setContent {
            Práctica03ComponentesListViewYSpinnerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RegistroConcierto()
                }
            }
        }
    }

    @Composable
    fun RegistroConcierto() {
        var nombreArtista by remember { mutableStateOf("") }
        var fecha by remember { mutableStateOf("") }
        var lugar by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("") }
        var cantidadEntradas by remember { mutableStateOf("") }
        var artistaBuscado by remember { mutableStateOf("") }
        var selectedConcierto by remember { mutableStateOf<Concierto?>(null) }
        var showDialog by remember { mutableStateOf(false) }

        fun limpiarCampos() {
            nombreArtista = ""
            fecha = ""
            lugar = ""
            precio = ""
            cantidadEntradas = ""
            artistaBuscado = ""
            selectedConcierto = null
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            TextField(value = nombreArtista, onValueChange = { nombreArtista = it }, label = { Text("Nombre del Artista") })
            TextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") })
            TextField(value = lugar, onValueChange = { lugar = it }, label = { Text("Lugar") })
            TextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
            TextField(value = cantidadEntradas, onValueChange = { cantidadEntradas = it }, label = { Text("Cantidad de Entradas") })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombreArtista.isEmpty() || fecha.isEmpty() || lugar.isEmpty() || precio.isEmpty() || cantidadEntradas.isEmpty()) {
                        Toast.makeText(this@MainActivity, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                    } else {
                        val nuevoConcierto = Concierto(
                            nombreArtista = nombreArtista,
                            fecha = fecha,
                            lugar = lugar,
                            precio = precio.toDouble(),
                            cantidadEntradas = cantidadEntradas.toInt()
                        )
                        conciertos.add(nuevoConcierto)
                        Log.d("Registro", "Concierto registrado: ${nuevoConcierto.nombreArtista}.")
                        Toast.makeText(this@MainActivity, "Concierto registrado: ${nuevoConcierto.nombreArtista}.", Toast.LENGTH_SHORT).show()
                        limpiarCampos()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = artistaBuscado, onValueChange = { artistaBuscado = it }, label = { Text("Buscar Artista") })

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val artistaBuscadoTrimmed = artistaBuscado.trim()
                    Log.d("Busqueda", "Buscando artista: $artistaBuscadoTrimmed")
                    Toast.makeText(this@MainActivity, "Buscando: $artistaBuscadoTrimmed", Toast.LENGTH_SHORT).show()

                    val conciertoEncontrado = conciertos.find { it.nombreArtista.equals(artistaBuscadoTrimmed, ignoreCase = true) }
                    if (conciertoEncontrado != null) {
                        selectedConcierto = conciertoEncontrado
                        showDialog = true
                    } else {
                        Toast.makeText(this@MainActivity, "Concierto no encontrado.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(conciertos) { concierto ->
                    Text(
                        text = "${concierto.nombreArtista} - ${concierto.fecha}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                selectedConcierto = concierto
                                showDialog = true
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (selectedConcierto != null) {
                        showDialog = true
                    } else {
                        Toast.makeText(this@MainActivity, "No hay registro para mostrar.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mostrar Registro")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    limpiarCampos()
                    Toast.makeText(this@MainActivity, "Campos limpiados.", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Limpiar")
            }
        }

        if (showDialog && selectedConcierto != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Detalles del Concierto") },
                text = {
                    Column {
                        Text(
                            "Artista: ${selectedConcierto?.nombreArtista}\n" +
                                    "Fecha: ${selectedConcierto?.fecha}\n" +
                                    "Lugar: ${selectedConcierto?.lugar}\n" +
                                    "Precio: ${selectedConcierto?.precio}\n" +
                                    "Cantidad de Entradas: ${selectedConcierto?.cantidadEntradas}"
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                conciertos.remove(selectedConcierto)
                                Log.d("Eliminación", "Concierto eliminado: ${selectedConcierto?.nombreArtista}.")
                                Toast.makeText(this@MainActivity, "Concierto eliminado: ${selectedConcierto?.nombreArtista}.", Toast.LENGTH_SHORT).show()
                                showDialog = false
                                limpiarCampos()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Eliminar")
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }
}
