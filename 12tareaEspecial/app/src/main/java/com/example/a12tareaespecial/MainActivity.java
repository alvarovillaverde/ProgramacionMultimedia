package com.example.a12tareaespecial;

import android.content.Intent;
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

        Button boton1 = findViewById(R.id.button);
        EditText etGalones = findViewById(R.id.editTextNumber);
        EditText etPintas = findViewById(R.id.editTextNumber2);
        TextView tvResultado = findViewById(R.id.resultado);

        // Recibir el resultado devuelto en litros desde MainActivity2
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("LITROS")) {
            String res = intent.getStringExtra("LITROS");
            tvResultado.setText(res + " l");
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("GALONES", etGalones.getText().toString());
                intent.putExtra("PINTAS", etPintas.getText().toString());
                startActivity(intent);
            }
        });
    }
}