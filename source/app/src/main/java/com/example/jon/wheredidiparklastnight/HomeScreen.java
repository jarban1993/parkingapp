package com.example.jon.wheredidiparklastnight;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.location.Location;
import android.location.LocationManager;
import android.location.Criteria;

import com.google.android.gms.maps.model.LatLng;

public class HomeScreen extends AppCompatActivity {

    private static EditText space, floor, meter;
    private static Button park;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        space = (EditText) findViewById(R.id.spacenum);
        floor = (EditText) findViewById(R.id.floornum);
        meter = (EditText) findViewById(R.id.edit_meter);
        park = (Button) findViewById(R.id.park_button);


        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), ConfirmPage.class);
                String spaceval = space.getText().toString();
                String floorval = floor.getText().toString();
                String meterval = meter.getText().toString();
                intent.putExtra("ParkingSpace", spaceval);
                intent.putExtra("ParkingFloor", floorval);
                intent.putExtra("ParkingMeter", meterval);
                startActivity(intent);
            }
        });
    }

}
