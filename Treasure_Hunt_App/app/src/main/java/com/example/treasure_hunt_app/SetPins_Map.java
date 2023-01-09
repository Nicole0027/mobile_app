package com.example.treasure_hunt_app;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;
import static android.content.Intent.parseIntent;

import static java.lang.Boolean.getBoolean;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

public class SetPins_Map extends Fragment {

    private static int pinNumber;
    private int numberOfDeletedPin;
    private boolean clickToDeletePin;
    private List<Marker> markerList ;
    private Intent sendMarkerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_setpins__map, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.pinsMap);
        
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng x = new LatLng(45.74618, 21.227217);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(x, 15));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                            if(clickToDeletePin)
                            {

                                Marker p = googleMap.addMarker(new MarkerOptions().position(latLng).title(Integer.toString(numberOfDeletedPin)));
                                p.showInfoWindow();
                            }
                            else
                            {
                                pinNumber++;
                                Marker p = googleMap.addMarker(new MarkerOptions().position(latLng).title(Integer.toString(pinNumber)));
                                p.showInfoWindow();
                            }
                            clickToDeletePin = false;

                    }
                });

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) 
                    {
                            clickToDeletePin = true;
                            numberOfDeletedPin = Integer.parseInt(marker.getTitle());
                            marker.remove();
                            return true;
                    }
                });


            }

        });

        return view;
    }
}