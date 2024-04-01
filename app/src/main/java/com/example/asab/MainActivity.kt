package com.example.asab
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import kotlin.math.log
import Model.Letras
import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import java.util.*
import kotlin.collections.ArrayList
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asab.letras
class MainActivity : AppCompatActivity() {

    private lateinit var btnGuardar: Button;//Creo la variable boton
    private lateinit var btnMaracas: Button;
    private lateinit var btnTambor: Button;
    private lateinit var NombresListView: ListView;
    private lateinit var Txt_Buscar: EditText;


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
        NombresListView= findViewById(R.id.NombresListView)
        Txt_Buscar =findViewById(R.id.Txt_Buscar)


        var Nombres = arrayOf<String>()
        for (elemento in letras.nombres){
            Nombres +=elemento
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Nombres)
        NombresListView.adapter = adapter

        NombresListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            letras.entero = position
            PasarActividadDetalle()
        }

        Txt_Buscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar este método
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
                // No es necesario implementar este método
            }
        })

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

    private fun PasarActividadDetalle() {
        // Crear un Intent para la actividad de destino
        val intent = Intent(this, Detalle::class.java)

        // Iniciar la actividad usando el Intent
        startActivity(intent)
    }



}
