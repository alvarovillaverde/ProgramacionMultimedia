package com.example.a10imageview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView; // Lo declaramos aquí para reusarlo

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

        Button boton = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView); // Usamos R.id.imageView

        boton.setOnClickListener(v -> cambiaAvatar());
    }

    private void cambiaAvatar() {
        Random r = new Random();
        int numAvatar = r.nextInt(16) + 1; // Genera del 1 al 16

        String avatar = "avatar_" + numAvatar;

        // Buscamos la ID del drawable por nombre
        int resID = getResources().getIdentifier(avatar, "drawable", getPackageName());

        // Comprobamos que el drawable exista y cargamos
        if (resID != 0 && imageView != null) {
            imageView.setImageResource(resID);
        }
    }
}