package com.example.sensoresguia

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    // Instancias
    private lateinit var detalle: TextView
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private var existeSensorProximidad: Boolean = false
    private lateinit var listadoSensores: List<Sensor>

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.xml.activity_main)

        // Asociar con componente gráfico
        detalle = findViewById(R.id.txtDetalle)

        // Gestionar los sensores del dispositivo
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    @SuppressLint("SetTextI18n")
    fun clickListado(view: View?) {
        // Gestionar los sensores del dispositivo
        listadoSensores = sensorManager.getSensorList(Sensor.TYPE_ALL)
        detalle.setBackgroundColor(Color.WHITE)
        detalle.text = "Lista de sensores del dispositivo"

        // Mostrar cada sensor identificado
        for (sensor in listadoSensores) {
            detalle.text = "${detalle.text}\nNombre: ${sensor.name}\nVersión: ${sensor.version}"
        }
    }

    @SuppressLint("SetTextI18n")
    fun clickMagnetico(view: View?) {
        // Validar la existencia del sensor magnético
        val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        if (magneticSensor != null) {
            Toast.makeText(applicationContext, "El dispositivo tiene sensor magnético.", Toast.LENGTH_SHORT).show()
            sensor = magneticSensor

            // Mostrar datos del sensor
            detalle.setBackgroundColor(Color.GRAY)
            detalle.text = "Propiedades del sensor Magnético:\nNombre: ${sensor.name}\nVersión: ${sensor.version}\nFabricante: ${sensor.vendor}"
        } else {
            Toast.makeText(applicationContext, "El dispositivo no cuenta con sensor magnético.", Toast.LENGTH_SHORT).show()
        }
    }

    fun clickProximidad(view: View?) {
        // Validar la existencia del Sensor de proximidad
        val proximidadSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (proximidadSensor != null) {
            existeSensorProximidad = true
            detalle.text = "El dispositivo tiene sensor: ${proximidadSensor.name}"
            detalle.setBackgroundColor(Color.GREEN)
        } else {
            detalle.text = "No se cuenta con sensor de proximidad"
            existeSensorProximidad = false
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Evaluar el valor para realizar alguna acción
        if (existeSensorProximidad && event != null) {
            val valorCambio = event.values[0]
            if (valorCambio < 1.0) {
                detalle.textSize = 30f
                detalle.setBackgroundColor(Color.BLUE)
                detalle.setTextColor(Color.WHITE)
                detalle.text = "\nCERCA $valorCambio"
            } else {
                detalle.textSize = 14f
                detalle.setBackgroundColor(Color.GREEN)
                detalle.setTextColor(Color.BLACK)
                detalle.text = "\nLEJOS $valorCambio"
            }
        } else {
            Toast.makeText(applicationContext, "Sin cambios.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Implementar si es necesario
    }

    // Sensor de Luz
    fun clickLuz(view: View?) {
        val luzSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (luzSensor != null) {
            detalle.text = "Propiedades del sensor de Luz:\nNombre: ${luzSensor.name}\nVersión: ${luzSensor.version}\nFabricante: ${luzSensor.vendor}"
            detalle.setBackgroundColor(Color.YELLOW)
        } else {
            Toast.makeText(this, "El dispositivo no cuenta con sensor de luz.", Toast.LENGTH_SHORT).show()
        }
    }

    // Sensor de Temperatura
    fun clickTemperatura(view: View?) {
        val temperaturaSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if (temperaturaSensor != null) {
            detalle.text = "Propiedades del sensor de Temperatura:\nNombre: ${temperaturaSensor.name}\nVersión: ${temperaturaSensor.version}\nFabricante: ${temperaturaSensor.vendor}"
            detalle.setBackgroundColor(Color.RED)
        } else {
            Toast.makeText(this, "El dispositivo no cuenta con sensor de temperatura.", Toast.LENGTH_SHORT).show()
        }
    }

    // Sensor de Acelerómetro
    fun clickAcelerometro(view: View?) {
        val acelerometroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (acelerometroSensor != null) {
            detalle.text = "Propiedades del sensor Acelerómetro:\nNombre: ${acelerometroSensor.name}\nVersión: ${acelerometroSensor.version}\nFabricante: ${acelerometroSensor.vendor}"
            detalle.setBackgroundColor(Color.CYAN)
        } else {
            Toast.makeText(this, "El dispositivo no cuenta con sensor de acelerómetro.", Toast.LENGTH_SHORT).show()
        }
    }

    // Sensor de Giroscopio
    fun clickGiroscopio(view: View?) {
        val giroscopioSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if (giroscopioSensor != null) {
            detalle.text = "Propiedades del sensor Giroscopio:\nNombre: ${giroscopioSensor.name}\nVersión: ${giroscopioSensor.version}\nFabricante: ${giroscopioSensor.vendor}"
            detalle.setBackgroundColor(Color.MAGENTA)
        } else {
            Toast.makeText(this, "El dispositivo no cuenta con sensor de giroscopio.", Toast.LENGTH_SHORT).show()
        }
    }

}
