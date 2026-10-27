package com.example.a13gettingresults

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvFecha: TextView = findViewById(R.id.textView3)
        val tvHora: TextView = findViewById(R.id.textView4)
        val btnFusionar: Button = findViewById(R.id.button2)

        val txtFecha = intent.getStringExtra("FECHA") ?: ""
        val txtHora = intent.getStringExtra("HORA") ?: ""

        tvFecha.text = txtFecha
        tvHora.text = txtHora

        btnFusionar.setOnClickListener {
            val fusion = "$txtFecha $txtHora"

            // Devolución del resultado mediante Intent
            val intentResultado = Intent()
            intentResultado.putExtra("FUSION", fusion)
            setResult(Activity.RESULT_OK, intentResultado)

            // Cierre obligatorio de la segunda actividad
            finish()
        }
    }
}