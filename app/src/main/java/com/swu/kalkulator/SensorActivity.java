package com.swu.kalkulator;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    Sensor accel, gyro;
    TextView tv;
    String a = "-", g = "-";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        tv = new TextView(this);
        tv.setTextSize(18);
        tv.setPadding(48, 48, 48, 48);
        setContentView(tv);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override protected void onResume() {
        super.onResume();
        if (accel != null) sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
        if (gyro != null) sm.registerListener(this, gyro, SensorManager.SENSOR_DELAY_UI);
    }

    @Override protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        String s = String.format(Locale.US, "X: %.2f\nY: %.2f\nZ: %.2f",
                e.values[0], e.values[1], e.values[2]);
        if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) a = s;
        else if (e.sensor.getType() == Sensor.TYPE_GYROSCOPE) g = s;
        tv.setText("ACCELEROMETER (m/s²)\n" + a + "\n\nGYROSCOPE (rad/s)\n" + g);
    }

    @Override public void onAccuracyChanged(Sensor s, int acc) {}
}