package com.example.asab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticaMaracas : AppCompatActivity() {

    private lateinit var BtnVolverMain : Button


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

    }

    override fun onResume() {
        super.onResume()

        BtnVolverMain.setOnClickListener{
            PasarActivityMain()
        }

    }

    private fun PasarActivityMain() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, MainActivity::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

}