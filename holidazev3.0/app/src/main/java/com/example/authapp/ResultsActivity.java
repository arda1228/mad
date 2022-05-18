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
    //  declaring variables
    private TextView currency, price, tvairlineName0, tvairlineCode0, tvflightNumber0, tvdurationInSeconds0, tvdepartureAirportCode0, tvarrivalAirportCode0, tvairlineName1, tvairlineCode1, tvflightNumber1, tvdurationInSeconds1, tvdepartureAirportCode1, tvarrivalAirportCode1;
    private JSONObject leg0, leg1;
    private CardView cardView1, cardView2;
    private String minCurrencyCode, minRoundedAmount, varairlineName0, airlineCode0, flightNumber0, durationInSeconds0, departureAirportCode0, arrivalAirportCode0;
    private String airlineName1, airlineCode1, flightNumber1, durationInSeconds1, departureAirportCode1, arrivalAirportCode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results2);

        // assigning respective view to variable
        currency = findViewById(R.id.currency);
        price = findViewById(R.id.price);

        // assigning respective view to variable
        tvairlineName0 = findViewById(R.id.airlineName0);
        tvflightNumber0 = findViewById(R.id.flightNumber0);
        tvdurationInSeconds0 = findViewById(R.id.durationInSeconds0);
        tvdepartureAirportCode0 = findViewById(R.id.departureAirportCode0);
        tvarrivalAirportCode0 = findViewById(R.id.arrivalAirportCode0);

        // assigning respective view to variable
        tvairlineName1 = findViewById(R.id.airlineName1);
        tvflightNumber1 = findViewById(R.id.flightNumber1);
        tvdurationInSeconds1 = findViewById(R.id.durationInSeconds1);
        tvdepartureAirportCode1 = findViewById(R.id.departureAirportCode1);
        tvarrivalAirportCode1 = findViewById(R.id.arrivalAirportCode1);

        // getting values to be displayed from search
        minCurrencyCode = getIntent().getStringExtra("minCurrencyCode");
        minRoundedAmount = getIntent().getStringExtra("minRoundedAmount");
        String jsonString0 = getIntent().getStringExtra("minCurrentOfferLeg0");
        String jsonString1 = getIntent().getStringExtra("minCurrentOfferLeg1");


        try {
            // displaying the results
            // converting received string of results to JSONObjects for processing
            JSONObject leg0 = new JSONObject(jsonString0);
            JSONObject leg1 = new JSONObject(jsonString1);

            // converting flight time from seconds to hours for departure leg
            int leg0durationInSecondsINTEGER =Integer.parseInt(String.valueOf(leg0.get("durationInSeconds")));
            int leg0durationInMinutesINTEGER = (int) (Math.floor(leg0durationInSecondsINTEGER/60));
            int leg0durationHOURS = (int) Math.floor(leg0durationInMinutesINTEGER/60);
            int leg0durationMINUTES = leg0durationInMinutesINTEGER-(leg0durationHOURS*60);

            // converting flight time from seconds to hours for return leg
            int leg1durationInSecondsINTEGER =Integer.parseInt(String.valueOf(leg1.get("durationInSeconds")));
            int leg1durationInMinutesINTEGER = (int) (Math.floor(leg1durationInSecondsINTEGER/60));
            int leg1durationHOURS = (int) Math.floor(leg1durationInMinutesINTEGER/60);
            int leg1durationMINUTES = leg1durationInMinutesINTEGER-(leg1durationHOURS*60);

//          updating values of textviews for departure leg
            tvairlineName0.setText(String.valueOf(leg0.get("airlineName")));
            tvflightNumber0.setText("Flight No.: " + String.valueOf(leg0.get("flightNumber")));
            tvdurationInSeconds0.setText("Flight Time: " + String.valueOf(leg0durationHOURS)+"h"+String.valueOf(leg0durationMINUTES)+"m");
            tvdepartureAirportCode0.setText("From: " + String.valueOf(leg0.get("departureAirportCode")));
            tvarrivalAirportCode0.setText("To: " + String.valueOf(leg0.get("arrivalAirportCode")));

            //          updating values of textviews for return leg
            tvairlineName1.setText(String.valueOf(leg1.get("airlineName")));
            tvflightNumber1.setText("Flight No.: " + String.valueOf(leg1.get("flightNumber")));
            tvdurationInSeconds1.setText("Flight Time: " + String.valueOf(leg1durationHOURS)+"h"+String.valueOf(leg1durationMINUTES)+"m");
            tvdepartureAirportCode1.setText("From: " + String.valueOf(leg1.get("departureAirportCode")));
            tvarrivalAirportCode1.setText("To: " + String.valueOf(leg1.get("arrivalAirportCode")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

//          updating values of textviews for currency and total price
        currency.setText(minCurrencyCode);
        price.setText(minRoundedAmount);
        stopService(new Intent(this, MyService.class)); // stops search music playing
    }
}