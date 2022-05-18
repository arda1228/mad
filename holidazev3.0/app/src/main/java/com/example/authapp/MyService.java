package com.example.authapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    //  declaring variable to play sound
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; //don't want to bind the service this time
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // when class is called, music plays until it is stopped explicitly
        Log.v("TAG", "in service");
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        return START_STICKY; //means service will be explicitly started and stopped
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
