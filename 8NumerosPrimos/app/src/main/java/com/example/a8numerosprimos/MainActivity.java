package com.example.a8numerosprimos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTexto = ((EditText) findViewById(R.id.editTextNumber)).getText().toString();

                if (!inputTexto.isEmpty()){
                    int posicion = Integer.parseInt(inputTexto);

                    int numeroBuscado = getNumeroPrimo(posicion);

                    String resultado = "El numero buscado es: " + numeroBuscado;

                    if (numeroBuscado == -1) {
                        resultado = "La posición debe estar entre 1 y 1000";
                    }

                    textView.setText(resultado);

                }
            }
        });
    }
    protected int getNumeroPrimo(int posicion) {
        if (posicion <= 0) return -1; // Validación para posiciones no válidas
        if (posicion > 1000) return -1;

        int contadorPrimos = 0;
        int numeroActual = 2; // El primer número primo posible

        while (contadorPrimos < posicion) {
            if (esPrimo(numeroActual)) {
                contadorPrimos++;
            }
            if (contadorPrimos < posicion) {
                numeroActual++;
            }
        }

        return numeroActual;
    }
    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Tiene un divisor, no es primo
            }
        }
        return true; // Es primo
    }
}