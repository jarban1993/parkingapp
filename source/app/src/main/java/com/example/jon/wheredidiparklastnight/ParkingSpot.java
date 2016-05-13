// Name: Jonathan Arban
// Course: CSC 415
// Semester: Spring 2016
// Instructor: Dr. Pulimood
// Project name: Where Did I Park Last Night?
// Description: Saves data on parking space number, garage floor, and timed meter
// Filename: ParkingSpot.java
// Description: Makes a parking spot object
// Last modified on: 5/1/16

package com.example.jon.wheredidiparklastnight;

import android.widget.EditText;

/**
 * Created by Jon on 4/30/16.
 */
public class ParkingSpot {
    EditText spacenumber;
    EditText floornumber;
    EditText meter;
    EditText address;

    public ParkingSpot(EditText spacenumber, EditText floornumber, EditText meter, EditText address){
        this.spacenumber = spacenumber;
        this.floornumber = floornumber;
        this.meter = meter;
        this.address = address;
    }

    public EditText getSpacenumber(){
        return spacenumber;
    }

    public EditText getFloornumber(){
        return floornumber;
    }

    public EditText getMeter(){
        return meter;
    }

    public EditText getAddress(){
        return address;
    }
}
