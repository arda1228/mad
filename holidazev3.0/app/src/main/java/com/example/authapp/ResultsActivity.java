package com.example.authapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultsActivity extends AppCompatActivity {
    private TextView currency, price, tvairlineName0, tvairlineCode0, tvflightNumber0, tvdurationInSeconds0, tvdepartureAirportCode0, tvarrivalAirportCode0, tvairlineName1, tvairlineCode1, tvflightNumber1, tvdurationInSeconds1, tvdepartureAirportCode1, tvarrivalAirportCode1;
    private JSONObject leg0, leg1;
    private CardView cardView1, cardView2;
    private String minCurrencyCode, minRoundedAmount, varairlineName0, airlineCode0, flightNumber0, durationInSeconds0, departureAirportCode0, arrivalAirportCode0;
    private String airlineName1, airlineCode1, flightNumber1, durationInSeconds1, departureAirportCode1, arrivalAirportCode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results2);

        currency = findViewById(R.id.currency);
        price = findViewById(R.id.price);
        tvairlineName0 = findViewById(R.id.airlineName0);
        tvflightNumber0 = findViewById(R.id.flightNumber0);
        tvdurationInSeconds0 = findViewById(R.id.durationInSeconds0);
        tvdepartureAirportCode0 = findViewById(R.id.departureAirportCode0);
        tvarrivalAirportCode0 = findViewById(R.id.arrivalAirportCode0);

        tvairlineName1 = findViewById(R.id.airlineName1);
        tvflightNumber1 = findViewById(R.id.flightNumber1);
        tvdurationInSeconds1 = findViewById(R.id.durationInSeconds1);
        tvdepartureAirportCode1 = findViewById(R.id.departureAirportCode1);
        tvarrivalAirportCode1 = findViewById(R.id.arrivalAirportCode1);




        minCurrencyCode = getIntent().getStringExtra("minCurrencyCode");
        minRoundedAmount = getIntent().getStringExtra("minRoundedAmount");
        String jsonString0 = getIntent().getStringExtra("minCurrentOfferLeg0");
        String jsonString1 = getIntent().getStringExtra("minCurrentOfferLeg1");


        try {
            JSONObject leg0 = new JSONObject(jsonString0);
            JSONObject leg1 = new JSONObject(jsonString1);

            int leg0durationInSecondsINTEGER =Integer.parseInt(String.valueOf(leg0.get("durationInSeconds")));
            int leg0durationInMinutesINTEGER = (int) (Math.floor(leg0durationInSecondsINTEGER/60));
            int leg0durationHOURS = (int) Math.floor(leg0durationInMinutesINTEGER/60);
            int leg0durationMINUTES = leg0durationInMinutesINTEGER-(leg0durationHOURS*60);

            int leg1durationInSecondsINTEGER =Integer.parseInt(String.valueOf(leg1.get("durationInSeconds")));
            int leg1durationInMinutesINTEGER = (int) (Math.floor(leg1durationInSecondsINTEGER/60));
            int leg1durationHOURS = (int) Math.floor(leg1durationInMinutesINTEGER/60);
            int leg1durationMINUTES = leg1durationInMinutesINTEGER-(leg1durationHOURS*60);

            tvairlineName0.setText(String.valueOf(leg0.get("airlineName")));
            tvflightNumber0.setText("Flight No.: " + String.valueOf(leg0.get("flightNumber")));
            tvdurationInSeconds0.setText("Flight Time: " + String.valueOf(leg0durationHOURS)+"h"+String.valueOf(leg0durationMINUTES)+"m");
            tvdepartureAirportCode0.setText("From: " + String.valueOf(leg0.get("departureAirportCode")));
            tvarrivalAirportCode0.setText("To: " + String.valueOf(leg0.get("arrivalAirportCode")));

            tvairlineName1.setText(String.valueOf(leg1.get("airlineName")));
            tvflightNumber1.setText("Flight No.: " + String.valueOf(leg1.get("flightNumber")));
            tvdurationInSeconds1.setText("Flight Time: " + String.valueOf(leg1durationHOURS)+"h"+String.valueOf(leg1durationMINUTES)+"m");
            tvdepartureAirportCode1.setText("From: " + String.valueOf(leg1.get("departureAirportCode")));
            tvarrivalAirportCode1.setText("To: " + String.valueOf(leg1.get("arrivalAirportCode")));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        currency.setText(minCurrencyCode);
        price.setText(minRoundedAmount);
        stopService(new Intent(this, MyService.class));
    }
}