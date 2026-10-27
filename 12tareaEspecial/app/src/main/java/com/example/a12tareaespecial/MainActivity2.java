package com.example.a12tareaespecial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private String resultadoLitros = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnConvertir = findViewById(R.id.btnConvertir); // Botón CONVERTIR
        Button btnRegresar = findViewById(R.id.btnRegresar);   // Botón REGRESAR

        TextView tvGalones = findViewById(R.id.textView3);
        TextView tvPintas = findViewById(R.id.textView4);
        TextView tvResultado = findViewById(R.id.tvResultadoLitros);

        String strGalones = "0";
        String strPintas = "0";

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("GALONES") != null) strGalones = intent.getStringExtra("GALONES");
            if (intent.getStringExtra("PINTAS") != null) strPintas = intent.getStringExtra("PINTAS");
        }

        // Parseo seguro evitando campos vacíos
        int galones = strGalones.isEmpty() ? 0 : Integer.parseInt(strGalones);
        int pintas = strPintas.isEmpty() ? 0 : Integer.parseInt(strPintas);

        // Validar límite máximo de 7 pintas (1 galón = 8 pintas)
        if (pintas > 7) {
            pintas = 7;
        }

        // Mostrar valores recibidos en los TextView
        tvGalones.setText(String.valueOf(galones));
        tvPintas.setText(String.valueOf(pintas));

        final int galonesFinal = galones;
        final int pintasFinal = pintas;

        // BOTÓN CONVERTIR: Realiza el cálculo de litros y lo muestra
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Conversión: 1 Galón = 4.5461 L | 1 Pinta = 0.5863 L
                double litros = (galonesFinal * 4.5461) + (pintasFinal * 0.5863);

                // Formatear a 4 decimales
                resultadoLitros = String.format(Locale.US, "%.4f", litros);

                tvResultado.setText(resultadoLitros + " l");
            }
        });

        // BOTÓN REGRESAR: Devuelve el resultado en litros a la primera Activity
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVolver = new Intent(MainActivity2.this, MainActivity.class);
                intentVolver.putExtra("LITROS", resultadoLitros);
                startActivity(intentVolver);
                finish();
            }
        });
    }
}