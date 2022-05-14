package com.example.myapi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapi.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText cityEd, cityEd2;
    Button btnFetch;
    String departing, destination;
    private DatePickerDialog datePickerDialog,datePickerDialog2;
    private Button dateButton, dateButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityEd = findViewById(R.id.city_et);
        cityEd2 = findViewById(R.id.city_et2);
        btnFetch = findViewById(R.id.btn_fet);
        btnFetch.setOnClickListener(this);

        initDatePicker();
        initDatePicker2();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        dateButton2 = findViewById(R.id.datePickerButton2);
        dateButton2.setText(getTodaysDate());

    }

    // date stuff begins

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                String date2 = makeDateString(day+1,month,year);
                dateButton2.setText(date2);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
//        day++;

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()+(24*60*60000));
        cal.add(cal.MONTH,6);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());

    }

    private void initDatePicker2()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton2.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog2.getDatePicker().setMinDate(System.currentTimeMillis()+(2*24*60*60000));
        cal.add(cal.MONTH,6);
        datePickerDialog2.getDatePicker().setMaxDate(cal.getTimeInMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void openDatePicker2(View view)
    {
        datePickerDialog2.show();
    }

    private String getAPIFormat(String rawDate)
    {
        String[] rawDateSplit = rawDate.split("\\s+");

        if(rawDateSplit[0].equals("JAN"))
            rawDateSplit[0] = String.valueOf(1);
        if(rawDateSplit[0].equals("FEB"))
            rawDateSplit[0] = String.valueOf(2);
        if(rawDateSplit[0].equals("MAR"))
            rawDateSplit[0] = String.valueOf(3);
        if(rawDateSplit[0].equals("APR"))
            rawDateSplit[0] = String.valueOf(4);
        if(rawDateSplit[0].equals("MAY"))
            rawDateSplit[0] = String.valueOf(5);
        if(rawDateSplit[0].equals("JUN"))
            rawDateSplit[0] = String.valueOf(6);
        if(rawDateSplit[0].equals("JUL"))
            rawDateSplit[0] = String.valueOf(7);
        if(rawDateSplit[0].equals("AUG"))
            rawDateSplit[0] = String.valueOf(8);
        if(rawDateSplit[0].equals("SEP"))
            rawDateSplit[0] = String.valueOf(9);
        if(rawDateSplit[0].equals("OCT"))
            rawDateSplit[0] = String.valueOf(10);
        if(rawDateSplit[0].equals("NOV"))
            rawDateSplit[0] = String.valueOf(11);
        if(rawDateSplit[0].equals("DEC"))
            rawDateSplit[0] = String.valueOf(12);

        //default should never happen
        return rawDateSplit[2]+"-"+rawDateSplit[0]+"-"+rawDateSplit[1];
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_fet ) {
            departing = cityEd.getText().toString();
            destination = cityEd2.getText().toString();
            try {
                getData(departing, destination);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getData(String departing, String destination) throws MalformedURLException {
        Uri uri = Uri.parse("https://www.expedia.com/api/flight/search?departureDate="+getAPIFormat(String.valueOf(dateButton.getText()))+"&returnDate="+getAPIFormat(String.valueOf(dateButton2.getText()))+"&departureAirport=" + departing.toUpperCase(Locale.ROOT) + "&arrivalAirport=" + destination.toUpperCase(Locale.ROOT) + "&nonStopFlight=true")
        .buildUpon().build();
        URL url = new URL(uri.toString());
        Log.v("TAG", String.valueOf(url));

        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
             URL url = urls [0];
             String data = null;
            try {
                 data = NetworkUtils.makeHTTPRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String data) {
            try {
                parseJson(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public void parseJson (String data) throws JSONException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject legsReferenceObject = new JSONObject();
//            Log.v("TAG",String.valueOf(jsonObject.get("errors")));
//            JSONArray errorsArray = (JSONArray) jsonObject.getJSONArray("errors");
//            JSONObject errorsObj = (JSONObject) errorsArray.get(0);
//            if (errorsObj.get("errorCode") == "INVALID_INPUT") {
//                Log.v("TAG", "Legs array empty");
//                Toast.makeText(getApplicationContext(),"one invalid airport code/ no direct flights found",Toast.LENGTH_SHORT).show();
//            }
//            else
                if (jsonObject.getJSONArray("legs").length() !=0) {
                for (int index = 0; index < jsonObject.getJSONArray("legs").length(); index++) {
                    JSONArray segmentsArr = new JSONArray();
                    JSONObject currentSegmentObj = new JSONObject();
                    JSONObject currentLegElement = (JSONObject) jsonObject.getJSONArray("legs").get(index);
                    for (int indexInner = 0; indexInner < currentLegElement.getJSONArray("segments").length(); indexInner++) {
                        JSONObject currentSegmentElement = (JSONObject) currentLegElement.getJSONArray("segments").get(indexInner);
                        currentSegmentObj.put("airlineName", currentSegmentElement.get("airlineName"));
                        currentSegmentObj.put("airlineCode", currentSegmentElement.get("airlineCode"));
                        currentSegmentObj.put("flightNumber", currentSegmentElement.get("flightNumber"));
                        currentSegmentObj.put("durationInSeconds", currentSegmentElement.get("durationInSeconds"));
                        currentSegmentObj.put("departureAirportCode", currentSegmentElement.get("departureAirportCode"));
                        currentSegmentObj.put("arrivalAirportCode", currentSegmentElement.get("arrivalAirportCode"));
                        segmentsArr.put(currentSegmentObj);
                    }
                    String legId = (String) currentLegElement.get("legId");
                    legsReferenceObject.put(legId, segmentsArr);
                }

                JSONArray offersAdvanced = new JSONArray();
                // NEW START
                int minRoundedAmount = 1000000;
                String minCurrencyCode = "";
                JSONObject minCurrentOfferLeg0 = new JSONObject();
                JSONObject minCurrentOfferLeg1 = new JSONObject();
                Boolean thisIndexIsNewMin = false;
                // NEW END
                for (int index = 0; index < jsonObject.getJSONArray("offers").length(); index++) {
                    JSONObject currentOffer = new JSONObject();
                    JSONArray currentOfferLegs = new JSONArray();
                    String targetLegID;
                    JSONObject offers = (JSONObject) jsonObject.getJSONArray("offers").get(index);

                    for (int indexInner = 0; indexInner < offers.getJSONArray("legIds").length(); indexInner++) {
                        targetLegID = (String) offers.getJSONArray("legIds").get(indexInner);
                        currentOfferLegs.put(legsReferenceObject.get(targetLegID));
                    }
                    JSONObject totalFarePriceObj = (JSONObject) offers.get("totalFarePrice");
                    //NEW START
                    if (index == 0) {
//                    minRoundedAmount = (Integer) totalFarePriceObj.get("roundedAmount");
                        minRoundedAmount = Integer.parseInt(String.valueOf(totalFarePriceObj.get("roundedAmount")));
                        minCurrencyCode = (String) totalFarePriceObj.get("currencyCode");
                        thisIndexIsNewMin = true;
                    } else {
                        thisIndexIsNewMin = false;
                        if (Integer.parseInt(String.valueOf(totalFarePriceObj.get("roundedAmount"))) < minRoundedAmount) {
//                        minRoundedAmount = (Integer) totalFarePriceObj.get("roundedAmount");
                            minRoundedAmount = Integer.parseInt(String.valueOf(totalFarePriceObj.get("roundedAmount")));
                            minCurrencyCode = (String) totalFarePriceObj.get("currencyCode");
                            thisIndexIsNewMin = true;
                        }
                    }
                    //NEW END

                    currentOffer.put("currentOfferLegs", currentOfferLegs);
                    currentOffer.put("roundedAmount", totalFarePriceObj.get("roundedAmount"));
                    currentOffer.put("currencyCode", totalFarePriceObj.get("currencyCode"));
                    //NEW START
                    if (thisIndexIsNewMin) {
                        JSONArray myArray0 = (JSONArray) currentOfferLegs.getJSONArray(0);
                        JSONArray myArray1 = (JSONArray) currentOfferLegs.getJSONArray(1);

                        minCurrentOfferLeg0 = (JSONObject) myArray0.get(0);
                        minCurrentOfferLeg1 = (JSONObject) myArray1.get(0);
                    }
                    // NEW END

                    offersAdvanced.put(currentOffer);


                }
//            Log.v("TAG", ""+offersAdvanced.get(0)+"");
                Log.v("TAG", "" + minCurrencyCode + "");
                Log.v("TAG", "" + minRoundedAmount + "");
                Log.v("TAG", "" + minCurrentOfferLeg0 + "");
                Log.v("TAG", "" + minCurrentOfferLeg1 + "");

                    Log.v("TAG", String.valueOf(dateButton.getText()));
                    Log.v("TAG", String.valueOf(dateButton2.getText()));

                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("minCurrencyCode", minCurrencyCode);
                intent.putExtra("minRoundedAmount", String.valueOf(minRoundedAmount));
                intent.putExtra("minCurrentOfferLeg0", minCurrentOfferLeg0.toString()); //cast to json object in next act
                intent.putExtra("minCurrentOfferLeg1", minCurrentOfferLeg1.toString()); //cast to json object in next act
                startActivity(intent);
            } else {
                Log.v("TAG", "Legs array empty");
                Toast.makeText(getApplicationContext(),"one invalid airport code/ no direct flights found",Toast.LENGTH_SHORT).show();
                }
        }
    }
}