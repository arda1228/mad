package com.example.authapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    //  declaring variables
    private Button logout, bucketList, search, userGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // assigning respective view to variable
        logout = (Button) findViewById(R.id.signOut);

        // responding to multiple touch events
        logout.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "logging out", Toast.LENGTH_SHORT).show();
                    // signals to firebase that user has logged out
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    super.onLongPress(e);
                }
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) { // ensures that user doesn't accidentally log out
                    Toast.makeText(getApplicationContext(), "long press to log out", Toast.LENGTH_SHORT).show();
                    return super.onSingleTapConfirmed(e);
                }
            });
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        // assigning respective view to variable
        bucketList = (Button) findViewById(R.id.bucketList);

        // responding to click event
        bucketList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moves to chosen page
                startActivity(new Intent(ProfileActivity.this, BucketList.class));
            }
        });

        // assigning respective view to variable
        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moves to chosen page
                startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
            }
        });

        // assigning respective view to variable
        userGuide = (Button) findViewById(R.id.userGuide);

// responding to click event
        userGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moves to chosen page
                startActivity(new Intent(ProfileActivity.this, UserGuideActivity.class));
            }
        });
    }
}