package com.example.asab

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PracticaTambor : AppCompatActivity() {

    private lateinit var BtnVolver : Button
    private lateinit var BtnTambor : Button
    private lateinit var tamborSound: MediaPlayer
    private val soundChannel = Channel<Unit>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practica_tambor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        BtnVolver = findViewById(R.id.BtnVolverTambor)
        tamborSound = MediaPlayer.create(this, R.raw.tambor)
        BtnTambor = findViewById<Button>(R.id.button3)
        BtnTambor.setOnClickListener {
            soundChannel.trySend(Unit)

            coroutineScope.launch {
                while (true) {
                    // Esperar a recibir una señal en el canal
                    soundChannel.receive()

                    // Reproducir el sonido del tambor
                    tamborSound.start()

                    // Esperar un segundo
                    delay(2000)

                    // Detener el sonido del tambor
                    tamborSound.pause()

                }
            }
        }


    }

    override fun onResume() {
        super.onResume()
        BtnVolver.setOnClickListener{
            PasarActivityMain()
        }
    }

    private fun PasarActivityMain() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, MainActivity::class.java)
        if (tamborSound.isPlaying) {
            tamborSound.stop()
            tamborSound.reset()
        }
        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

}