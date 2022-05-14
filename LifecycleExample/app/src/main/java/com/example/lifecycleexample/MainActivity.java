package com.example.lifecycleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView9);
        EditText editText = findViewById(R.id.editText1);


        //displaying log when onCreate() called
        Log.v("TAG", "oncreate called");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("TAG", "onstart called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("TAG", "onstop called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("TAG", "onresume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("TAG", "onpause called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("TAG", "onrestart called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("TAG", "ondestroy called");
    }
}