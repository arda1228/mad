package com.example.gesturesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.gesture.Gesture;
import android.widget.TextView;

import static android.view.GestureDetector.*;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static TextView textView;
    private GestureDetectorCompat GestureDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        GestureDetect = new GestureDetectorCompat(this,this);
        GestureDetect.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        textView.setText("onSingleTapConfirmed" + motionEvent.toString());
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        textView.setText("onDoubleTap" + motionEvent.toString());
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        textView.setText("onDoubleTapEvent" + motionEvent.toString());
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        textView.setText("onDown" + motionEvent.toString());

        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        textView.setText("onShowPress" + motionEvent.toString());


    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        textView.setText("onSingleTapUp" + motionEvent.toString());

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        textView.setText("onScroll" + motionEvent.toString());

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        textView.setText("onLongPress" + motionEvent.toString());


    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        textView.setText("onFling" + motionEvent.toString() + motionEvent1.toString());

        return false;
    }
}