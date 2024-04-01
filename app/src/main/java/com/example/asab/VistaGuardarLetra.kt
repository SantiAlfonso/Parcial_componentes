package com.example.asab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import Model.Letras
import android.app.AlertDialog
import android.widget.EditText
import android.widget.Toast


private lateinit var BtnVolver : Button
private lateinit var BtnGuardarLetra : Button
private lateinit var Txt_Nombre: EditText
private lateinit var Txt_Letra: EditText
val letras = Letras()

class VistaGuardarLetra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_guardar_letra)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar el botón btnGuardar con la referencia del botón en el layout
        BtnVolver = findViewById(R.id.BtnVolver)
        BtnGuardarLetra = findViewById(R.id.BtnGuardarLetra)
        Txt_Nombre = findViewById(R.id.Txt_Nombre)
        Txt_Letra = findViewById(R.id.Txt_Letra)
        // Inicializar el adapter


    }

    override fun onResume() {
        super.onResume()

        BtnVolver.setOnClickListener{
            PasarActivityMain()
        }
        BtnGuardarLetra.setOnClickListener{
            val nombre = Txt_Nombre.text.toString()
            val lyrics = Txt_Letra.text.toString()

            if (nombre.isNotEmpty() && lyrics.isNotEmpty()) {
                val size = letras.nombres.size
                letras.letras += lyrics
                letras.nombres += nombre

                // Crear un mensaje con la información guardada
                val message = StringBuilder()

                for (i in 0 until letras.nombres.size) {
                    message.append("${letras.nombres[i]}\n\n")
                }

                // Crear el diálogo emergente
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder
                    .setTitle("La canción ha sido guardada")
                    .setMessage(message.toString())
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        PasarActivityMain()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            } else {
                Toast.makeText(this, "Por favor ingrese nombre y letra", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun PasarActivityMain() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, MainActivity::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }

}