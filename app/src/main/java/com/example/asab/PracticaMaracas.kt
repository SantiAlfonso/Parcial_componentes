package com.example.asab

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class PracticaMaracas : AppCompatActivity(), SensorEventListener {

    private lateinit var BtnVolverMain : Button
    private lateinit var sensorManager: SensorManager
    private lateinit var maracaSound: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practica_maracas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        BtnVolverMain = findViewById(R.id.BtnVolverMaracas)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        maracaSound = MediaPlayer.create(this, R.raw.maracas_sound)
        maracaSound.isLooping = true;
    }
    private fun playMaracaSound() {
        if (maracaSound != null && !maracaSound.isPlaying) {
            maracaSound.start()
        }
    }

    var estaSeccionMaracasActiva = false // Bandera para rastrear el estado de la sección de maracas
    var reproductorMusica: MediaPlayer? = null // Declarar reproductorMusica para el sonido

    override fun onResume() {
        super.onResume()

        estaSeccionMaracasActiva = true // Establecer bandera al entrar a la sección de maracas

        BtnVolverMain.setOnClickListener {
            PasarActivityMain()
            estaSeccionMaracasActiva = false // Limpiar bandera al salir de la sección de maracas

            // Detener sonido si se está reproduciendo
            reproductorMusica?.stop()
            reproductorMusica?.release() // Liberar recursos para evitar fugas
            reproductorMusica = null
        }

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    val TIEMPO_SIN_MOVIMIENTO = 1000// Milisegundos (1 segundo)
    var ultimoTiempoMovimiento = 0L

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER && estaSeccionMaracasActiva) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val tiempoActual = System.currentTimeMillis()
            val intensidadMovimiento = Math.sqrt(Math.pow(x.toDouble(), 2.0) + Math.pow(y.toDouble(), 2.0) + Math.pow(z.toDouble(), 2.0))
            val volumenFactor = 0.02 // Ajusta este valor según sea necesario
            val volumenSonido = intensidadMovimiento * volumenFactor


            // Detección de movimiento
            if (Math.abs(x) > 20 || Math.abs(y) > 20 || Math.abs(z) > 20) {
                ultimoTiempoMovimiento = tiempoActual
                // Reproducir sonido si se detecta movimiento
                if (reproductorMusica == null) {
                    reproductorMusica = MediaPlayer.create(this, R.raw.maracas_sound)
                    reproductorMusica?.start()
                }
                reproductorMusica?.setVolume(volumenSonido.toFloat(), volumenSonido.toFloat())
            } else {
                // Si no hay movimiento y ha pasado el tiempo sin movimiento, detener el sonido
                if (tiempoActual - ultimoTiempoMovimiento > TIEMPO_SIN_MOVIMIENTO) {
                    reproductorMusica?.stop()
                    reproductorMusica?.release()
                    reproductorMusica = null
                }

            }
        }
    }



    private fun PasarActivityMain() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, MainActivity::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

}