package com.swu.kalkulator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class GpsActivity extends AppCompatActivity {
    TextView tv;
    Button btnMaps;
    double lat, lng;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(48, 48, 48, 48);
        tv = new TextView(this); tv.setTextSize(18); tv.setText("Mengambil lokasi...");
        btnMaps = new Button(this); btnMaps.setText("Buka di Google Maps");
        btnMaps.setEnabled(false);
        root.addView(tv); root.addView(btnMaps);
        setContentView(root);

        btnMaps.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:" + lat + "," + lng + "?q=" + lat + "," + lng))));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        ambilLokasi();
    }

    @Override
    public void onRequestPermissionsResult(int rc, String[] p, int[] r) {
        super.onRequestPermissionsResult(rc, p, r);
        if (r.length > 0 && r[0] == PackageManager.PERMISSION_GRANTED) ambilLokasi();
        else tv.setText("Izin lokasi ditolak");
    }

    private void ambilLokasi() {
        try {
            LocationServices.getFusedLocationProviderClient(this)
                    .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener(loc -> {
                        if (loc == null) { tv.setText("Lokasi belum tersedia. Aktifkan GPS."); return; }
                        lat = loc.getLatitude(); lng = loc.getLongitude();
                        tv.setText("Latitude: " + lat + "\nLongitude: " + lng);
                        btnMaps.setEnabled(true);
                    })
                    .addOnFailureListener(e -> tv.setText("Gagal: " + e.getMessage()));
        } catch (SecurityException e) {
            tv.setText("Izin lokasi diperlukan");
        }
    }
}