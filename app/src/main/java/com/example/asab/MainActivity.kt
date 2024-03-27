package com.example.asab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var btnGuardar: Button;//Creo la variable boton
    private lateinit var btnMaracas: Button;
    private lateinit var btnTambor: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar el botón btnGuardar con la referencia del botón en el layout
        btnGuardar = findViewById(R.id.BotonGuardar)
        btnMaracas = findViewById(R.id.BotonMaraca)
        btnTambor = findViewById(R.id.BotonTambor)

    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "La actividad está iniciando.")
    }

    //Iniciamos el estado en espera para que el usuario interactue con la actividad
    override fun onResume() {
        super.onResume()
        btnGuardar.setOnClickListener {
            iniciarOtraActividad()
        }

        btnMaracas.setOnClickListener {
            PasarActividadMaracas()
        }

        btnTambor.setOnClickListener {
            PasarActividadTambor()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "La actividad está en pausa.")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "La actividad se detuvo.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "La actividad se reinicio.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "La actividad se destruyo.")
    }

    private fun iniciarOtraActividad() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, VistaGuardarLetra::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

    private fun PasarActividadMaracas() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, PracticaMaracas::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

    private fun PasarActividadTambor() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, PracticaTambor::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

}