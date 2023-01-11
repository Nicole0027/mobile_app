package com.example.treasure_hunt_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Game_Activity extends FragmentActivity implements OnMapReadyCallback {

    private static ArrayList<Marker> listOfPinsOfSelectedMap = new ArrayList<>();
    private static int pinCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        listOfPinsOfSelectedMap = ChooseMap_Activity.getMarkersForMapToPlay();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.gameMapTest);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng x = new LatLng(45.74618, 21.227217);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(x, 15));
        for (Marker mk : listOfPinsOfSelectedMap) {
            pinCount++;
            googleMap.addMarker(new MarkerOptions().position(mk.getPosition()).title("Pin:" + pinCount));
        }
    }
}