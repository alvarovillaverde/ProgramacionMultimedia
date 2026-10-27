package com.example.a13gettingresults

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var fecha: EditText
    private lateinit var hora: EditText
    private lateinit var resultado: TextView
    private lateinit var boton: Button

    // 1. Definición del Launcher para registrar la recepción del resultado
    private val launcherSegundaActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Comprobamos que el resultado sea OK
        if (result.resultCode == Activity.RESULT_OK) {
            val intentData: Intent? = result.data
            val cadenaFusionada = intentData?.getStringExtra("FUSION") ?: ""
            resultado.text = cadenaFusionada
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fecha = findViewById(R.id.editTextDate)
        hora = findViewById(R.id.editTextTime)
        resultado = findViewById(R.id.textView)
        boton = findViewById(R.id.button)

        val formateadorFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formateadorHora = SimpleDateFormat("HH:mm", Locale.getDefault())
        val ahora = Calendar.getInstance().time

        fecha.setText(formateadorFecha.format(ahora))
        hora.setText(formateadorHora.format(ahora))

        boton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("FECHA", fecha.text.toString())
            intent.putExtra("HORA", hora.text.toString())

            // 2. Función de partida actualizada usando el Launcher
            launcherSegundaActivity.launch(intent)
        }
    }
}