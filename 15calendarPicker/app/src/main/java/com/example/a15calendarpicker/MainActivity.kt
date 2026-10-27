package com.example.a15calendarpicker

import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var calendario: CalendarView
    private lateinit var boton: Button
    lateinit var inputFecha: EditText
    lateinit var outputFecha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calendario = findViewById<CalendarView>(R.id.calendarView)
        boton = findViewById<Button>(R.id.button)
        inputFecha = findViewById<EditText>(R.id.inputFecha)
        outputFecha = findViewById<EditText>(R.id.outputFecha)
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())


        calendario.setOnDateChangeListener { view, year, month, day ->
            outputFecha.setText("$day/${month + 1}/$year")
        }

        boton.setOnClickListener {
            val textoFecha = inputFecha.text.toString()

            if (textoFecha.isNotEmpty()) {
                try {
                    val fecha: Date? = formatoFecha.parse(textoFecha)
                    if (fecha != null) {
                        calendario.setDate(fecha.time, true, true)
                    }
                } catch (e: Exception) {
                    // El texto introducido no es una fecha válida
                    inputFecha.error = "Formato incorrecto (ej: 25/12/2026)"
                }
            }
        }
    }
}