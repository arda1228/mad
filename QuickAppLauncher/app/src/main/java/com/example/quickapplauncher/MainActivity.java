package com.example.quickapplauncher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // launching next activity in app
        Button secondActivityBtn = (Button)findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startIntent.putExtra("com.example.quickapplauncher.SOMETHING", "Hello World!");
                startActivity(startIntent);
            }
        });

        // launching next activity outside app
        Button googleBtn = (Button)findViewById(R.id.googleBtn);
        googleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String google = "http://www.nba.com";
                Uri webaddress = Uri.parse(google);

                Intent goToGoogle = new Intent(Intent.ACTION_VIEW, webaddress);
                if (goToGoogle.resolveActivity(getPackageManager()) != null){
                    startActivity(goToGoogle);
                }
            }
        });
    }
}