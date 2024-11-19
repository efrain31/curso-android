package com.example.camara

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FotosRecientesActivity : AppCompatActivity() {

    private lateinit var imgFoto1: ImageView
    private lateinit var imgFoto2: ImageView
    private lateinit var imgFoto3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotos_recientes)

        // Asociar las vistas
        imgFoto1 = findViewById(R.id.imgFoto1)
        imgFoto2 = findViewById(R.id.imgFoto2)
        imgFoto3 = findViewById(R.id.imgFoto3)

        // Obtener las tres fotos más recientes y mostrarlas
        mostrarFotosRecientes()
    }

    private fun mostrarFotosRecientes() {
        val resolver: ContentResolver = contentResolver
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(MediaStore.Images.Media._ID)
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val cursor = resolver.query(uri, projection, null, null, sortOrder)

        cursor?.let {
            if (cursor.moveToFirst()) {
                val columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

                // Cargar la primera imagen
                val imageId1 = cursor.getLong(columnIndexId)
                val imageUri1 = Uri.withAppendedPath(uri, imageId1.toString())
                imgFoto1.setImageURI(imageUri1)

                // Cargar la segunda imagen
                if (cursor.moveToNext()) {
                    val imageId2 = cursor.getLong(columnIndexId)
                    val imageUri2 = Uri.withAppendedPath(uri, imageId2.toString())
                    imgFoto2.setImageURI(imageUri2)
                }

                // Cargar la tercera imagen
                if (cursor.moveToNext()) {
                    val imageId3 = cursor.getLong(columnIndexId)
                    val imageUri3 = Uri.withAppendedPath(uri, imageId3.toString())
                    imgFoto3.setImageURI(imageUri3)
                }
            }
            cursor.close()
        }
    }
}
