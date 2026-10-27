package com.example.a11activityswitcher;

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
        EditText num1 = findViewById(R.id.editTextNumber);
        EditText num2 = findViewById(R.id.editTextNumber2);
        TextView resultado = findViewById(R.id.resultado);

        // Recibir el resultado
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("SUMA")) {
            String res = intent.getStringExtra("SUMA");
            resultado.setText(res);
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("NUM1", num1.getText().toString());
                intent.putExtra("NUM2", num2.getText().toString());
                startActivity(intent);
            }
        });
    }
}