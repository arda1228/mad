package com.example.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.buttonStart);
        stop = (Button) findViewById(R.id.buttonStop);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == start) {
            startService(new Intent(this, MyService.class));
        } else if (view == stop){
            stopService(new Intent(this, MyService.class));
        }
    }
}