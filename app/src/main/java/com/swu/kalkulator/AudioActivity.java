package com.swu.kalkulator;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AudioActivity extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(48, 48, 48, 48);
        Button play = new Button(this); play.setText("Play");
        Button stop = new Button(this); stop.setText("Stop");
        root.addView(play); root.addView(stop);
        setContentView(root);

        player = MediaPlayer.create(this, R.raw.musik);
        play.setOnClickListener(v -> { if (!player.isPlaying()) player.start(); });
        stop.setOnClickListener(v -> { if (player.isPlaying()) player.pause(); });
        player.start(); // langsung putar saat terbuka
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) { player.release(); player = null; }
    }
}