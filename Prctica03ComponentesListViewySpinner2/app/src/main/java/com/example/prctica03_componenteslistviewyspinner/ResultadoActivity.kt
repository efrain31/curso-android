package com.example.prctica03_componenteslistviewyspinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.prctica03_componenteslistviewyspinner.ui.theme.Práctica03ComponentesListViewYSpinnerTheme

class ResultadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Práctica03ComponentesListViewYSpinnerTheme {
                Surface {
                    val artistaBuscado = intent.getStringExtra("ARTISTA_BUSCADO")
                    MostrarResultado(artistaBuscado)
                }
            }
        }
    }

    @Composable
    fun MostrarResultado(artistaBuscado: String?) {
        Text(text = "Artista buscado: $artistaBuscado")
        // Aquí puedes agregar más detalles si es necesario
    }
}
