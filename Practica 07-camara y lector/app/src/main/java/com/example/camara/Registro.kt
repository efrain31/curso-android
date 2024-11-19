package com.example.camara

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Registro(
    val codigo: String,      // Código de barras o QR
    val descripcion: String  // Descripción del código
) : Parcelable
