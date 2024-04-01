package com.example.asab
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Toast
import com.example.asab.letras


private lateinit var TxtNombre: TextView
private lateinit var TxtLetra: TextView


class Detalle : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        TxtNombre = findViewById(R.id.TxtNombre)
        TxtLetra = findViewById(R.id.TxtLetra)

        val nombre = letras.nombres[letras.entero]
        val letra = letras.letras[letras.entero]

        // Establecer los valores en los TextView
        TxtNombre.text = "Nombre: $nombre"
        TxtLetra.text = "Letra: $letra"
    }

    override fun onResume() {
        super.onResume()
    }


}