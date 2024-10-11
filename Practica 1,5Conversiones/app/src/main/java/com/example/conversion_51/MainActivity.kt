package com.example.conversion_51

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class RadioActivity : AppCompatActivity() {
    //Instancias para componentes gráficos
    private lateinit var metros: EditText
    private lateinit var pies: RadioButton
    private lateinit var pulgadas: RadioButton
    private lateinit var yardas: RadioButton
    private lateinit var resultado: TextView
    private lateinit var opciones: RadioGroup
    //Declaración de instancia de clase
    private lateinit var obj: Convertidor
    //FORMATO PARA REPRESENTAR CANTIDAD DE DECIMALES
    private val formatoDecimales: DecimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociaciòn con objetos gráficos
        metros = findViewById(R.id.edtMetros)
        pies = findViewById(R.id.rbtPies)
        pulgadas = findViewById(R.id.rbtPulgadas)
        yardas = findViewById(R.id.rbtYardas)
        resultado = findViewById(R.id.txtResultado)
        opciones = findViewById(R.id.rgpOpciones)

        //Inicializar instancias de clase
        obj = Convertidor()
    }//onCreate

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.ibtnConvertir -> btnConvertir()
            R.id.ibtnMostrar -> btnMostrar()
            R.id.ibtnLimpiar -> btnLimpiar()
        }
    }//onClick

    fun btnConvertir() {
        //Validar que hay valor
        if (metros.text.isNotEmpty()) {
            // Obtener cantidad en metros (ahora como Double)
            obj.meter = metros.text.toString().toDouble()

            // Evaluar la opción seleccionada para calcular
            when {
                pies.isChecked -> obj.calculateFeet()
                pulgadas.isChecked -> obj.calculateInch()
                yardas.isChecked -> obj.calculateYard()
            }

            Toast.makeText(applicationContext, "Metros convertidos.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Ingresa cantidad en metros.", Toast.LENGTH_SHORT).show()
        }
    }//btnConvertir

    fun btnMostrar() {
        val res = """
            Cantidad de metros convertida a: 
            Pies: ${formatoDecimales.format(obj.feet)}
            Pulgadas: ${formatoDecimales.format(obj.inch)}
            Yardas: ${formatoDecimales.format(obj.yard)}
        """.trimIndent()
        resultado.text = res
    }//btnMostrar

    fun btnLimpiar() {
        metros.text = null
        resultado.text = "Cantidad de metros convertida a: \n"
        opciones.clearCheck()
        metros.requestFocus()
        obj = Convertidor()
    }//btnLimpiar
}//class


class Convertidor {
    //Atributos ahora como Double para permitir decimales
    var meter: Double = 0.0
    var feet: Double = 0.0
    var inch: Double = 0.0
    var yard: Double = 0.0

    fun calculateFeet() {
        if (this.meter > 0) {
            this.feet = this.meter * 3.2808
        }
    }//calcularPies

    fun calculateInch() {
        if (this.meter > 0) {
            this.inch = this.meter * 39.3701
        }
    }//calcularPulgadas

    fun calculateYard() {
        if (this.meter > 0) {
            this.yard = this.meter * 1.09361
        }
    }
}
