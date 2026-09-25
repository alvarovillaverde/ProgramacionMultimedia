package com.example.dos;

import android.os.Bundle;
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((TextView)findViewById(R.id.CAJA2)).append("onStart()\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView)findViewById(R.id.CAJA2)).append("onResume()\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((TextView)findViewById(R.id.CAJA2)).append("onDestroy()\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((TextView)findViewById(R.id.CAJA2)).append("onPause()\n");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((TextView)findViewById(R.id.CAJA2)).append("onStop()\n");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((TextView)findViewById(R.id.CAJA2)).append("onRestart()\n");
    }
}