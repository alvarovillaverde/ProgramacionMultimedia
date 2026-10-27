package com.example.a16timepicker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextClock
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker
    private lateinit var timeClock: TextClock
    private lateinit var horas: TextView
    private lateinit var introducirHoras: EditText
    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        timePicker = findViewById(R.id.timePicker)
        timeClock = findViewById(R.id.textClock)
        horas = findViewById(R.id.getTime)
        introducirHoras = findViewById(R.id.editTextTime)
        boton = findViewById(R.id.button)

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
            horas.text = time
        }

        boton.setOnClickListener {
            val time = introducirHoras.text.toString().trim()
            if (time.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce una hora (hh:mm)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val partes = time.split(":")
            if (partes.size == 2) {
                val hora = partes[0].toIntOrNull()
                val minuto = partes[1].toIntOrNull()

                if (hora != null && minuto != null && hora in 0..23 && minuto in 0..59) {
                    val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hora, minuto)
                    horas.text = formattedTime
                    timePicker.hour = hora
                    timePicker.minute = minuto
                } else {
                    Toast.makeText(this, "Hora no válida. Usa horas 00-23 y minutos 00-59", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Formato no válido. Usa el formato hh:mm", Toast.LENGTH_SHORT).show()
            }
        }

    }
}