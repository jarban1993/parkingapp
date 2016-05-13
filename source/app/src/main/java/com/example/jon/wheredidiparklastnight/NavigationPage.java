// Name: Jonathan Arban
// Course: CSC 415
// Semester: Spring 2016
// Instructor: Dr. Pulimood
// Project name: Where Did I Park Last Night?
// Description: Saves data on parking space number, garage floor, and timed meter
// Filename: NavigationPage.java
// Description: Establishes the data that is entered at the navigation screen
// Last modified on: 5/6/16

package com.example.jon.wheredidiparklastnight;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class NavigationPage extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    private static CountDownTimer countDownTimer;
    TextView parkingmeternav;
    private static Button stop;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Pass data from the previous page
        String space = getIntent().getStringExtra("ParkingSpaceNav");
        String floor = getIntent().getStringExtra("ParkingFloorNav");
        String meter = getIntent().getStringExtra("ParkingMeterNav");
        final String address = getIntent().getStringExtra("ParkingAddress");
        long millis = getIntent().getLongExtra("Milliseconds", 0);
        stop = (Button) findViewById(R.id.stop_button);

        //Display parking space
        TextView parkingspacenav = (TextView) findViewById(R.id.parkingspacenav);
        parkingspacenav.setText(space);

        //Display garage floor
        TextView parkingfloornav = (TextView) findViewById(R.id.parkingfloornav);
        parkingfloornav.setText(floor);

        //Display meter
        parkingmeternav = (TextView) findViewById(R.id.parkingmeternav);
        int time = (int) millis;
        startTimer(time);

        //Stop button that resets the activity
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent reset = new Intent(getApplicationContext(), HomeScreen.class);
                reset.putExtra("AddressVal", address);
                stopCountdown();
                startActivity(reset);
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng initial = new LatLng(40.267076, -74.779664);     //Location of car in this case the garage at TCNJ
        LatLng current = new LatLng(40.271396, -74.777633);     //Location of user in this case Forcina Mac Lab
        mMap.addMarker(new MarkerOptions().position(current).title("Current Location"));
        mMap.addMarker(new MarkerOptions().position(initial).title("Car"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 14));
    }









    //Meter Timer
    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                parkingmeternav.setText("Time Remaining: " + hms);//set text
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

}

