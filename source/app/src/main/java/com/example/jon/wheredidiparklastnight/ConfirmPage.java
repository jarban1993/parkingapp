package com.example.jon.wheredidiparklastnight;

import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.os.Build;
import android.view.Menu;
import android.app.Activity;

import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.TimeUnit;


public class ConfirmPage extends AppCompatActivity {

    private static CountDownTimer countDownTimer;
    private static TextView parkingmeter;
    private static Button navigate;
    long millis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_screen);

        navigate = (Button) findViewById(R.id.Navbutton);

        String space = getIntent().getStringExtra("ParkingSpace");
        String floor = getIntent().getStringExtra("ParkingFloor");
        String meter = getIntent().getStringExtra("ParkingMeter");
        String[] initial_loaction = getIntent().getStringArrayExtra("InitialLocation");



        final TextView parkingspace = (TextView)findViewById(R.id.ParkingSpaceText);
        parkingspace.setText("Parking Space: " + space);

        final TextView parkingfloor = (TextView)findViewById(R.id.ParkingFloorText);
        parkingfloor.setText("Floor Number: " + floor);

        parkingmeter = (TextView)findViewById(R.id.ParkingMeterText);
        final int noOfMinutes = Integer.parseInt(meter) * 60 * 1000; //converts minutes to milliseconds
        startTimer(noOfMinutes);


        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent2 = new Intent(getApplicationContext(), NavigationPage.class);
                String spaceval = parkingspace.getText().toString();
                String floorval = parkingfloor.getText().toString();
                String meterval = parkingmeter.getText().toString();
                intent2.putExtra("ParkingSpaceNav", spaceval);
                intent2.putExtra("ParkingFloorNav", floorval);
                intent2.putExtra("ParkingMeterNav", meterval);
                intent2.putExtra("Milliseconds", millis);
                startActivity(intent2);
            }
        });






    }

    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                parkingmeter.setText("Time Remaining: " + hms);//set text

            }

            public void onFinish() {
                countDownTimer = null;
            }


        }.start();

    }

    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    public void onBackPressed(){
        stopCountdown();
        super.onBackPressed();
    }
}
