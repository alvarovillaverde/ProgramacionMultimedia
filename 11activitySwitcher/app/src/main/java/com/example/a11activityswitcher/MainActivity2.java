package com.example.a11activityswitcher;

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

public class MainActivity2 extends AppCompatActivity {

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

        Button boton2 = findViewById(R.id.button);
        TextView num1 = findViewById(R.id.textView3);
        TextView num2 = findViewById(R.id.textView4);

        String numero1 = "0";
        String numero2 = "0";

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("NUM1") != null) numero1 = intent.getStringExtra("NUM1");
            if (intent.getStringExtra("NUM2") != null) numero2 = intent.getStringExtra("NUM2");
        }

        num1.setText(numero1);
        num2.setText(numero2);

        // Parseo seguro evitando campos vacíos
        int val1 = numero1.isEmpty() ? 0 : Integer.parseInt(numero1);
        int val2 = numero2.isEmpty() ? 0 : Integer.parseInt(numero2);

        int suma = val1 + val2;
        String sumaYa = String.valueOf(suma);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("SUMA", sumaYa);
                startActivity(intent);
                finish(); // Cerramos esta pantalla para liberar memoria y no amontonar ventanas
            }
        });
    }
}