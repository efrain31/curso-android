package com.example.deportes

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var txtEdad: EditText
    private lateinit var txtNacionalidad: EditText
    private lateinit var toggleTopMundial: ToggleButton
    private lateinit var radioGroupGenero: RadioGroup
    private lateinit var radMasculino: RadioButton
    private lateinit var radFemenino: RadioButton
    private lateinit var checkOlimpico: CheckBox
    private lateinit var checkProfesional: CheckBox
    private lateinit var btnAgregar: ImageButton
    private lateinit var btnBuscar: ImageButton
    private lateinit var btnLimpiar: ImageButton
    private lateinit var txtDatos: EditText  // Aquí se mostrarán los datos

    // Variables para almacenar el registro
    private var nombre: String = ""
    private var edad: Int = 0
    private var nacionalidad: String = ""
    private var esTopMundial: Boolean = false
    private var genero: String = ""
    private var esOlimpico: Boolean = false
    private var esProfesional: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos de la interfaz
        txtNombre = findViewById(R.id.txtNombre)
        txtEdad = findViewById(R.id.txtEdad)
        txtNacionalidad = findViewById(R.id.txtNacionalidad)
        toggleTopMundial = findViewById(R.id.toggleTopMundial)
        radioGroupGenero = findViewById(R.id.radioGroupGenero)
        radMasculino = findViewById(R.id.radMasculino)
        radFemenino = findViewById(R.id.radFemenino)
        checkOlimpico = findViewById(R.id.checkOlimpico)
        checkProfesional = findViewById(R.id.checkProfesional)
        btnAgregar = findViewById(R.id.imgbutAgregar)
        btnBuscar = findViewById(R.id.imgbutBuscar)
        btnLimpiar = findViewById(R.id.imgbutLimpiar)
        txtDatos = findViewById(R.id.txtDatos)  // Referencia a txtDatos donde se mostrarán los datos

        // Acción para el botón agregar
        btnAgregar.setOnClickListener {
            // Obtener la información ingresada
            nombre = txtNombre.text.toString()
            edad = txtEdad.text.toString().toIntOrNull() ?: 0
            nacionalidad = txtNacionalidad.text.toString()
            esTopMundial = toggleTopMundial.isChecked

            // Verificar el género seleccionado
            val selectedGeneroId = radioGroupGenero.checkedRadioButtonId
            genero = if (selectedGeneroId == R.id.radMasculino) {
                "Masculino"
            } else if (selectedGeneroId == R.id.radFemenino) {
                "Femenino"
            } else {
                "No especificado"
            }

            // Verificar si es tenista olímpico o profesional
            esOlimpico = checkOlimpico.isChecked
            esProfesional = checkProfesional.isChecked

            Toast.makeText(this, "Registro agregado", Toast.LENGTH_SHORT).show()
        }

        // Acción para el botón buscar
        btnBuscar.setOnClickListener {
            // Mostrar la información almacenada en el EditText (txtDatos)
            val info = """
                Nombre: $nombre
                Edad: $edad
                Nacionalidad: $nacionalidad
                Top Mundial: ${if (esTopMundial) "Sí" else "No"}
                Género: $genero
                Olímpico: ${if (esOlimpico) "Sí" else "No"}
                Profesional: ${if (esProfesional) "Sí" else "No"}
            """.trimIndent()

            txtDatos.setText(info)  // Muestra los datos en txtDatos
        }

        // Acción para el botón limpiar
        btnLimpiar.setOnClickListener {
            // Limpiar los campos de texto y opciones seleccionadas
            txtNombre.text.clear()
            txtEdad.text.clear()
            txtNacionalidad.text.clear()
            toggleTopMundial.isChecked = false
            radioGroupGenero.clearCheck()
            checkOlimpico.isChecked = false
            checkProfesional.isChecked = false
            txtDatos.text.clear()  // Limpiar el campo de texto donde se muestran los datos

            Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
        }
    }
}
