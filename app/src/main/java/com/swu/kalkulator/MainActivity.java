package com.swu.kalkulator;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Riwayat> dataRiwayat = new ArrayList<>();

    // Deklarasi variabel
    EditText EditNum, EditNum2;
    Button buttonPlus, buttonMin, buttonMulti,
            buttonDiv, buttonCalc, buttonClear;
    TextView textResult;

    String operatorTerpilih = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen
        EditNum = findViewById(R.id.editNum1);
        EditNum2 = findViewById(R.id.editNum2);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMin = findViewById(R.id.buttonMin);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonCalc = findViewById(R.id.buttonCalc);
        buttonClear = findViewById(R.id.buttonClear);

        textResult = findViewById(R.id.textResult);

        // Tombol tambah
        buttonPlus.setOnClickListener(v -> {
            operatorTerpilih = "+";
            Toast.makeText(this, "Operator + dipilih", Toast.LENGTH_SHORT).show();
        });

        // Tombol kurang
        buttonMin.setOnClickListener(v -> {
            operatorTerpilih = "-";
            Toast.makeText(this, "Operator - dipilih", Toast.LENGTH_SHORT).show();
        });

        // Tombol kali
        buttonMulti.setOnClickListener(v -> {
            operatorTerpilih = "x";
            Toast.makeText(this, "Operator x dipilih", Toast.LENGTH_SHORT).show();
        });

        // Tombol bagi
        buttonDiv.setOnClickListener(v -> {
            operatorTerpilih = ":";
            Toast.makeText(this, "Operator bagi dipilih", Toast.LENGTH_SHORT).show();
        });

        // Tombol hitung
        buttonCalc.setOnClickListener(v -> hitungHasil());

        // Tombol reset
        buttonClear.setOnClickListener(v -> {
            EditNum.setText("");
            EditNum2.setText("");

            textResult.setText("HASIL");
            operatorTerpilih = "";

            EditNum.requestFocus();
        });
    }

    // Method untuk menghitung hasil
    private void hitungHasil() {

        String input1 = EditNum.getText().toString().trim();
        String input2 = EditNum2.getText().toString().trim();

        // Validasi input
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this,
                    "Mohon isi kedua angka terlebih dahulu",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Validasi operator
        if (operatorTerpilih.isEmpty()) {
            Toast.makeText(this,
                    "Mohon pilih operator terlebih dahulu",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Konversi String menjadi double
            double angka1 = Double.parseDouble(input1);
            double angka2 = Double.parseDouble(input2);
            double hasil;

            // Proses perhitungan
            switch (operatorTerpilih) {

                case "+":
                    hasil = angka1 + angka2;
                    break;

                case "-":
                    hasil = angka1 - angka2;
                    break;

                case "x":
                    hasil = angka1 * angka2;
                    break;

                case ":":
                    if (angka2 == 0) {
                        Toast.makeText(this,
                                "Angka pembagi tidak boleh nol",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    hasil = angka1 / angka2;
                    break;

                default:
                    Toast.makeText(this,
                            "Operator tidak valid",
                            Toast.LENGTH_SHORT).show();
                    return;
            }

            // Menampilkan hasil
            if (hasil == Math.floor(hasil)
                    && Math.abs(hasil) < 1.0E15) {
                textResult.setText(
                        String.format(Locale.US, "%.0f", hasil));
            } else {
                textResult.setText(
                        String.format(Locale.US, "%s", hasil));
            }

            // Simpan ke riwayat
            String teksPerhitungan = input1 + " " + operatorTerpilih + " " + input2;
            dataRiwayat.add(new Riwayat(teksPerhitungan, textResult.getText().toString()));

            // Fitur secret calculator (NIM STI202403910 -> 0 3 9 1 0)
            if (Math.abs(hasil - 0) < 0.000001) {
                // Digit 1 = 0 -> Kamera
                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            } else if (Math.abs(hasil - 3) < 0.000001) {
                // Digit 2 = 3 -> Play audio
                startActivity(new Intent(this, AudioActivity.class));
            } else if (Math.abs(hasil - 9) < 0.000001) {
                // Digit 3 = 9 -> Tampil gambar
                startActivity(new Intent(this, ImageActivity.class));
            } else if (Math.abs(hasil - 1) < 0.000001) {
                // Digit 4 = 1 -> GPS
                startActivity(new Intent(this, GpsActivity.class));
            } else if (Math.abs(hasil - 10) < 0.000001) {
                // Digit 5 = 0 (bentrok dengan digit 1, jadi dipakai 10)
                // -> Accelerometer & Gyroscope
                startActivity(new Intent(this, SensorActivity.class));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Masukkan angka yang valid",
                    Toast.LENGTH_SHORT).show();
        }
    }
}