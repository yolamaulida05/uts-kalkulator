package com.swu.kalkulator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends Activity {

    Button btnKalkulator, btnAbout, btnMenuRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnKalkulator = findViewById(R.id.btnKalkulator);
        btnAbout = findViewById(R.id.btnAbout);
        btnMenuRiwayat = findViewById(R.id.btnMenuRiwayat);

        btnKalkulator.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnMenuRiwayat.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this, AboutActivity.class);
            startActivity(intent);
        });

    }
}