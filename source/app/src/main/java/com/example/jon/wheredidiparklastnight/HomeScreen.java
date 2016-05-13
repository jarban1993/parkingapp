// Name: Jonathan Arban
// Course: CSC 415
// Semester: Spring 2016
// Instructor: Dr. Pulimood
// Project name: Where Did I Park Last Night?
// Description: Saves data on parking space number, garage floor, and timed meter
// Filename: HomeScreen.java
// Description: Establishes the data that is entered at the home screen
// Last modified on: 5/12/16



package com.example.jon.wheredidiparklastnight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class HomeScreen extends AppCompatActivity {

    private static EditText space, floor, meter, address, rate;
    private static TextView rateview;
    private static Button park, last, calc;
    private static Spinner history;
    ParkingSpot parkingspot;
    LinkedList lastaddress = new LinkedList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Edit Text Fields
        space = (EditText) findViewById(R.id.spacenum);
        floor = (EditText) findViewById(R.id.floornum);
        meter = (EditText) findViewById(R.id.edit_meter);
        address = (EditText) findViewById(R.id.address);
        rate = (EditText) findViewById(R.id.rate);

        //Text View Field **ADDED FOR SOFTWARE ENGINEERING FINAL
        rateview = (TextView) findViewById(R.id.rateview);


        //Establish a parkingspot object
        parkingspot = new ParkingSpot(space, floor, meter, address);

        //Park Button
        park = (Button) findViewById(R.id.park_button);

        //Last Button
        last = (Button) findViewById(R.id.saveaddress);

        //Calc Button **ADDED FOR SOFTWARE ENGINEERING FINAL
        calc = (Button) findViewById(R.id.calc_button);


        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevaddress = getIntent().getStringExtra("AddressVal");
                lastaddress.add(prevaddress);
                address = (EditText) lastaddress.getLast();
            }
        });

        //Calculate Button **ADDED FOR SOFTWARE ENGINEERING FINAL
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });



        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), ConfirmPage.class);
                Intent intent3 = new Intent(getApplicationContext(), NavigationPage.class);
                String spaceval = parkingspot.getSpacenumber().getText().toString();
                String floorval = parkingspot.getFloornumber().getText().toString();
                String meterval = parkingspot.getMeter().getText().toString();
                String addressval = parkingspot.getAddress().getText().toString();
                intent.putExtra("ParkingSpace", spaceval);
                intent.putExtra("ParkingFloor", floorval);      //Sends data to the Confirm Page
                intent.putExtra("ParkingMeter", meterval);
                intent.putExtra("ParkingAddress", addressval);
                intent3.putExtra("ParkingAddress", addressval);
                startActivity(intent);
            }
        });
    }

    //Calculation for Rate **ADDED FOR SOFTWARE ENGINEERING FINAL
    void calculate(){
        Double metercalc = Double.parseDouble(meter.getText().toString());
        Double ratecalc = Double.parseDouble(rate.getText().toString());
        Double total = (ratecalc/60)*metercalc;
        rateview.setText('$' + total.toString());
    }

}
