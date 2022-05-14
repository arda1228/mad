package com.example.saveinstancestateexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewCount;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCount = findViewById(R.id.text_view_count);

        Button buttonDecrement = findViewById(R.id.button_decrement);
        Button buttonIncrement = findViewById(R.id.button_increment);

        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count");
            mTextViewCount.setText(String.valueOf(mCount));
        }
    }

    private void decrement() {
        mCount--;
        mTextViewCount.setText(String.valueOf(mCount));
    }

    private void increment() {
        mCount++;
        mTextViewCount.setText(String.valueOf(mCount));
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putInt("count", mCount);
    }
}